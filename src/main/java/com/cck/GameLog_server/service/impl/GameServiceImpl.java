package com.cck.GameLog_server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cck.GameLog_server.dto.CreateGameDTO;
import com.cck.GameLog_server.dto.GameDTO;
import com.cck.GameLog_server.dto.UpdateGameStatusDTO;
import com.cck.GameLog_server.model.Game;
import com.cck.GameLog_server.model.GameStatus;
import com.cck.GameLog_server.model.User;
import com.cck.GameLog_server.repository.GameRepository;
import com.cck.GameLog_server.repository.UserRepository;
import com.cck.GameLog_server.service.IGameService;



@Service
public class GameServiceImpl implements IGameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameServiceImpl(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    // Implement the methods defined in IGameService interface

    @Override
    public GameDTO addGame(CreateGameDTO createGameDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        

        if (gameRepository.findByExternalApiIdAndUserId(createGameDTO.getExternalApiId(), userId).isPresent()) {
            throw new RuntimeException("Jogo já está na biblioteca");
        }
        Game game = new Game();
        game.setTitle(createGameDTO.getTitle());
        game.setImageUrl(createGameDTO.getImageUrl());
        game.setStatus(GameStatus.QUERO_JOGAR);
        game.setUser(user);
        game.setGenres(createGameDTO.getGenres());
        game.setExternalApiId(createGameDTO.getExternalApiId());
        game.setReleaseYear(createGameDTO.getReleaseYear());
        game.setPlatforms(createGameDTO.getPlatforms());

        Game savedGame = gameRepository.save(game);
        return convertToDTO(savedGame);
    }
    @Override
    public List<GameDTO> findGamesByUserId(Long userId) {
        List<Game> games = gameRepository.findByUserId(userId);
        return games.stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Override
    public GameDTO updateGameStatus(Long gameId, Long userId, UpdateGameStatusDTO updateGameStatusDTO) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        if (!game.getUser().getId().equals(userId)) {
            throw new RuntimeException("Ação não autorizada");
        }
        game.setStatus(updateGameStatusDTO.getStatus());
        gameRepository.save(game);
        return convertToDTO(game);
    }
    @Override
    public void removeGameFromLibrary(Long gameId, Long userId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));
        if (!game.getUser().getId().equals(userId)) {
            throw new RuntimeException("Ação não autorizada");
        }
        gameRepository.delete(game);
    }

    private GameDTO convertToDTO(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setImageUrl(game.getImageUrl());
        gameDTO.setReleaseYear(game.getReleaseYear());
        gameDTO.setGenres(game.getGenres());
        gameDTO.setPlatforms(game.getPlatforms());
        gameDTO.setStatus(game.getStatus());
        return gameDTO;
    }

}

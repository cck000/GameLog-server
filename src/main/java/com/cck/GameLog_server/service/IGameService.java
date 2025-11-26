package com.cck.GameLog_server.service;

import java.util.List;

import com.cck.GameLog_server.dto.CreateGameDTO;
import com.cck.GameLog_server.dto.GameDTO;
import com.cck.GameLog_server.dto.UpdateGameStatusDTO;

public interface IGameService {
    
    GameDTO addGame(CreateGameDTO createGameDTO, Long userId);

    List<GameDTO> findGamesByUserId(Long userId);
    
    
    GameDTO updateGameStatus(Long gameId, Long userId, UpdateGameStatusDTO updateGameStatusDTO);
    void removeGameFromLibrary(Long gameId, Long userId);
}

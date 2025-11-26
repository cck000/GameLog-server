package com.cck.GameLog_server.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cck.GameLog_server.dto.CreateGameDTO;
import com.cck.GameLog_server.dto.GameDTO;
import com.cck.GameLog_server.dto.UpdateGameStatusDTO;
import com.cck.GameLog_server.model.User;
import com.cck.GameLog_server.service.IGameService;

@RestController
@RequestMapping("/api/games") // <-- URL base mais limpa
public class GameController {

    private final IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    // Método auxiliar para pegar o usuário logado (evita repetir código)
    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    // 1. Listar jogos da MINHA biblioteca
    // GET http://localhost:8080/api/games
    @GetMapping
    public ResponseEntity<List<GameDTO>> getMyLibrary() {
        Long userId = getAuthenticatedUser().getId();
        List<GameDTO> games = gameService.findGamesByUserId(userId);
        return ResponseEntity.ok(games);
    }

    // 2. Adicionar jogo à MINHA biblioteca
    // POST http://localhost:8080/api/games
    @PostMapping
    public ResponseEntity<GameDTO> addGame(@RequestBody CreateGameDTO createGameDTO) {
        Long userId = getAuthenticatedUser().getId();
        GameDTO newGame = gameService.addGame(createGameDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }

    // 3. Atualizar Status
    // PATCH http://localhost:8080/api/games/{gameId}
    @PatchMapping("/{gameId}")
    public ResponseEntity<GameDTO> updateStatus(
            @PathVariable Long gameId,
            @RequestBody UpdateGameStatusDTO statusDTO) {
            
        Long userId = getAuthenticatedUser().getId();
        GameDTO updatedGame = gameService.updateGameStatus(gameId, userId, statusDTO);
        return ResponseEntity.ok(updatedGame);
    }

    // 4. Remover jogo
    // DELETE http://localhost:8080/api/games/{gameId}
    @DeleteMapping("/{gameId}")
    public ResponseEntity<Void> removeGame(@PathVariable Long gameId) {
        Long userId = getAuthenticatedUser().getId();
        gameService.removeGameFromLibrary(gameId, userId);
        return ResponseEntity.noContent().build();
    }
}
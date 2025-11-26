package com.cck.GameLog_server.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cck.GameLog_server.dto.GameSearchResultDTO;
import com.cck.GameLog_server.model.User;
import com.cck.GameLog_server.service.IGameSearchService;

@RestController
@RequestMapping("/api/search")
public class GameSearchController {

    private final IGameSearchService gameSearchService;

    public GameSearchController(IGameSearchService gameSearchService) {
        this.gameSearchService = gameSearchService;
    }

    @GetMapping("/games")
    public List<GameSearchResultDTO> searchGames(@RequestParam String query) {
        // Pega o ID do usuário logado
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return gameSearchService.searchGames(query, user.getId());
    }

}

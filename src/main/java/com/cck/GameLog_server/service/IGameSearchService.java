package com.cck.GameLog_server.service;

import java.util.List;

import com.cck.GameLog_server.dto.GameSearchResultDTO;

public interface IGameSearchService {

    List<GameSearchResultDTO> searchGames(String query, Long userId);
    
}

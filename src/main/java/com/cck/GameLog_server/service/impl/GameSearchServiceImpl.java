package com.cck.GameLog_server.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cck.GameLog_server.dto.GameSearchResultDTO;
import com.cck.GameLog_server.dto.RawgApiResponseDTO;
import com.cck.GameLog_server.dto.RawgGenreDTO;
import com.cck.GameLog_server.repository.GameRepository;
import com.cck.GameLog_server.service.IGameSearchService;

@Service
public class GameSearchServiceImpl implements IGameSearchService {

    private final RestTemplate restTemplate;
    private final GameRepository gameRepository;

    @Value("${rawg.api.url}")
    private String rawgApiUrl;
    @Value("${rawg.api.key}")
    private String rawgApiKey;
    
    public GameSearchServiceImpl(RestTemplate restTemplate, GameRepository gameRepository) {
        this.restTemplate = restTemplate;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameSearchResultDTO> searchGames(String query, Long userId) {
        String url = String.format("%s/games?search=%s&key=%s", rawgApiUrl, query, rawgApiKey);
        RawgApiResponseDTO response = restTemplate.getForObject(url, RawgApiResponseDTO.class);

        if (response == null || response.getResults().isEmpty()) {
            return Collections.emptyList();
        }
        List<Long> externalApiIds = response.getResults().stream()
            .map(game -> game.getId())
            .collect(Collectors.toList());
        Set<Long> existingIdsInLibrary = gameRepository.findExistingExternalApiIdsByUserId(userId, externalApiIds);

        return response.getResults().stream().map(rawgGame -> {
            GameSearchResultDTO dto = new GameSearchResultDTO();
            dto.setExternalApiId(rawgGame.getId());
            dto.setTitle(rawgGame.getName());
            dto.setImageUrl(rawgGame.getBackgroundImage());

            try {
                if (rawgGame.getReleasedDate() != null) {
                    dto.setReleaseYear(Integer.parseInt(rawgGame.getReleasedDate().substring(0, 4)));
                }
            } catch (Exception e) {
            }
            if (rawgGame.getGenres() != null && !rawgGame.getGenres().isEmpty()) {
                // Pega a lista de objetos, extrai só o nome e junta com vírgula
                String genresString = rawgGame.getGenres().stream()
                        .map(RawgGenreDTO::getName)
                        .collect(Collectors.joining(", "));
                
                dto.setGenres(genresString);
            }
            if (rawgGame.getPlatforms() != null && !rawgGame.getPlatforms().isEmpty()) {
                String platformsString = rawgGame.getPlatforms().stream()// Note o "getPlatform()" extra aqui antes do getName()
                        
                        .map(wrapper -> wrapper.getPlatform().getName())
                        .collect(Collectors.joining(", "));
                
                dto.setPlatforms(platformsString);
            }
            dto.setInLibrary(existingIdsInLibrary.contains(rawgGame.getId()));
            return dto;
        }).collect(Collectors.toList());
        
    }


}

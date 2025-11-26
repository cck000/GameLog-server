package com.cck.GameLog_server.dto;
import com.cck.GameLog_server.model.GameStatus;

import lombok.Data;

@Data
public class GameDTO {
    private Long id;
    
    private String title;
    private String imageUrl;
    private Integer releaseYear;
    private String genres;
    private String platforms;
    private GameStatus status;
}

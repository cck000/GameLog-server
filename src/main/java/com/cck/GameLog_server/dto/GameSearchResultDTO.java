package com.cck.GameLog_server.dto;
import lombok.Data;
@Data
public class GameSearchResultDTO {
    private Long externalApiId;
    private String title;
    private String imageUrl;
    private Integer releaseYear;
    private String genres;
    private String platforms;
    private boolean isInLibrary;
    
}

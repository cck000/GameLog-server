package com.cck.GameLog_server.dto;
import lombok.Data;
@Data
public class CreateGameDTO {
    private String title;
    private String genres;
    private String platforms;
    private String imageUrl;
    private Integer releaseYear;
    private Long externalApiId;
}

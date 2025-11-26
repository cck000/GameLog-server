package com.cck.GameLog_server.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class RawgGameDTO {
    private Long id;
    private String name;

    @JsonProperty("background_image")
    private String backgroundImage;

    @JsonProperty("released")
    private String releasedDate;
    private List<RawgGenreDTO> genres;
    private List<RawgPlatformDTO> platforms;


}

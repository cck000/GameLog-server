package com.cck.GameLog_server.dto;

import lombok.Data;

@Data
public class RawgPlatformDTO {
    private RawgPlatformDetailsDTO platform;
    @Data
    public static class RawgPlatformDetailsDTO {
        private Long id;
        private String name;
        private String slug;
    }
}
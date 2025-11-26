package com.cck.GameLog_server.dto;
import java.util.List;

import lombok.Data;
@Data
public class RawgApiResponseDTO {
    private List<RawgGameDTO> results;
}

package com.cck.GameLog_server.dto;
import com.cck.GameLog_server.model.GameStatus;

import lombok.Data;
@Data
public class UpdateGameStatusDTO {
    private GameStatus status;
}

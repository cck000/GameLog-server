package com.cck.GameLog_server.service;
import com.cck.GameLog_server.dto.LoginRequestDTO;
import com.cck.GameLog_server.dto.LoginResponseDTO;
import com.cck.GameLog_server.dto.RegisterUserDTO;
import com.cck.GameLog_server.dto.UserDTO;

public interface IAuthService {
    UserDTO register(RegisterUserDTO registerUserDTO);
    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
    
}
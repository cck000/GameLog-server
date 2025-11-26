package com.cck.GameLog_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cck.GameLog_server.dto.LoginRequestDTO;
import com.cck.GameLog_server.dto.LoginResponseDTO;
import com.cck.GameLog_server.dto.RegisterUserDTO;
import com.cck.GameLog_server.dto.UserDTO;
import com.cck.GameLog_server.service.IAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    // POST http://localhost:8080/api/auth/register
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterUserDTO registerDTO) {
        UserDTO newUser = authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // POST http://localhost:8080/api/auth/login
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginDTO) {
        LoginResponseDTO response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }
}
package com.cck.GameLog_server.service.impl;

import com.cck.GameLog_server.dto.LoginRequestDTO;
import com.cck.GameLog_server.dto.LoginResponseDTO;
import com.cck.GameLog_server.dto.RegisterUserDTO;
import com.cck.GameLog_server.dto.UserDTO;
import com.cck.GameLog_server.infra.security.TokenService;
import com.cck.GameLog_server.model.User;
import com.cck.GameLog_server.repository.UserRepository;
import com.cck.GameLog_server.service.IAuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, 
                           PasswordEncoder passwordEncoder, 
                           TokenService tokenService, 
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDTO register(RegisterUserDTO registerDTO) {
        // 1. Verificar se usuário já existe
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        // 2. Criar entidade e CRIPTOGRAFAR a senha
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword())); // <--- A MÁGICA DO BCRYPT

        // 3. Salvar
        User savedUser = userRepository.save(user);

        // 4. Retornar DTO
        UserDTO dto = new UserDTO();
        dto.setId(savedUser.getId());
        dto.setUsername(savedUser.getUsername());
        dto.setEmail(savedUser.getEmail());
        return dto;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginDTO) {
        // 1. Autenticar usando o AuthenticationManager do Spring
        // Isso vai chamar o CustomUserDetailsService e verificar a senha criptografada automaticamente
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        // 2. Se não deu erro acima, o login é válido. Buscamos o usuário.
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow();

        // 3. Gerar o Token JWT
        String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }
}
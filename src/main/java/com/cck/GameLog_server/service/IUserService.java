package com.cck.GameLog_server.service;

import com.cck.GameLog_server.dto.UserDTO;
import java.util.List;

public interface IUserService {

    // Buscar dados públicos de um usuário pelo ID (para exibir perfil)
    UserDTO getUserById(Long id);

    // Buscar todos os usuários (útil para testes ou admin, cuidado em produção!)
    List<UserDTO> getAllUsers();
    
    // Futuramente você pode adicionar:
    // void updateUser(Long id, UpdateUserDTO data);
    // void deleteUser(Long id);
}
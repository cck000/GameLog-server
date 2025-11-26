package com.cck.GameLog_server.repository;

import com.cck.GameLog_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Usado no login para achar o usuário pelo nome
    Optional<User> findByUsername(String username);
    
    // (Opcional) Útil para validar se um email já existe no cadastro
    Optional<User> findByEmail(String email);
    
    // O JpaRepository já nos dá o findById(Long id) automaticamente!
}
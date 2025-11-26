package com.cck.GameLog_server.repository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cck.GameLog_server.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g.externalApiId FROM Game g WHERE g.user.id = :userId AND g.externalApiId IN :externalApiIds")
    Set<Long> findExistingExternalApiIdsByUserId(Long userId, List<Long> externalApiIds);
    List<Game> findByUserId(Long userId);
    Optional<Game> findByExternalApiIdAndUserId(Long externalApiId, Long userId);
}

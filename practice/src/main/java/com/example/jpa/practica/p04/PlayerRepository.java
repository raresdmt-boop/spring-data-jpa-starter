package com.example.jpa.practica.p04;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findFirstByOrderByPointsDesc();

    List<Player> findTop3ByOrderByPointsDesc();
}

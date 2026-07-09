package com.example.demo.unulamulti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByTeamName(String name);

    List<Player> findByPosition(String position);
}

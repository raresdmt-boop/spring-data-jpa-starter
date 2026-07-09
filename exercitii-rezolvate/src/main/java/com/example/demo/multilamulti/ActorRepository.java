package com.example.demo.multilamulti;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findByFilmsTitle(String title);
}

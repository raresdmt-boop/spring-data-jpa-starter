package com.example.jpa.provocari.c08;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    default Optional<Alert> findFirstByOrderByPriorityDesc() {
        throw new UnsupportedOperationException("TODO c08");
    }
}

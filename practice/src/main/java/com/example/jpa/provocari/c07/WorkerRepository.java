package com.example.jpa.provocari.c07;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    default Double averageSalary(String department) {
        throw new UnsupportedOperationException("TODO c07");
    }
}

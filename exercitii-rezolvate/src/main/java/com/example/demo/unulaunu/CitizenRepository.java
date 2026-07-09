package com.example.demo.unulaunu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    Optional<Citizen> findByPassportCode(String code);
}

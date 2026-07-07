package com.example.jpa.practica.p02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAgeBetween(int minAge, int maxAge);

    List<Person> findByAgeGreaterThanEqual(int minAge);
}

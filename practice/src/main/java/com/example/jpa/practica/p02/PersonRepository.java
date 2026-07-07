package com.example.jpa.practica.p02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    // Cerință: persoanele cu vârsta între minAge și maxAge (inclusiv la ambele capete).
    default List<Person> findByAgeBetween(int minAge, int maxAge) {
        throw new UnsupportedOperationException("TODO p02.1");
    }

    // Cerință: persoanele cu vârsta mai mare sau egală cu minAge.
    default List<Person> findByAgeGreaterThanEqual(int minAge) {
        throw new UnsupportedOperationException("TODO p02.2");
    }
}

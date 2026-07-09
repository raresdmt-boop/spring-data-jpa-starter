package com.example.jpa.practica.p01;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Cerință: întoarce contul cu username-ul dat, împachetat în Optional
    // (gol dacă nu există). Metodă derivată; șterge `default` + corpul.


    Optional<Account> findByUsername(String username);

//    default Optional<Account> findByUsername(String username) {
//        throw new UnsupportedOperationException("TODO p01");
//    }
}

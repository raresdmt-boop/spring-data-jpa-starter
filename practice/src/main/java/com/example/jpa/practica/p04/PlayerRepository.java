package com.example.jpa.practica.p04;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Cerință: jucătorul cu cele mai multe puncte (unul singur). Cuvinte cheie:
    // `First` + `OrderBy...Desc`.
    default Optional<Player> findFirstByOrderByPointsDesc() {
        throw new UnsupportedOperationException("TODO p04.1");
    }

    // Cerință: primii 3 jucători după puncte, descrescător. Cuvânt cheie: `Top3`.
    default List<Player> findTop3ByOrderByPointsDesc() {
        throw new UnsupportedOperationException("TODO p04.2");
    }
}

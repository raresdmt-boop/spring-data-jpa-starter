package com.example.jpa.practica.p05;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // Cerință: notițele dintr-o categorie, folosind @Query JPQL (nu derived).
    // Adaugă @Query cu :category legat prin @Param, șterge `default` + corpul.
    default List<Note> findInCategory(String category) {
        throw new UnsupportedOperationException("TODO p05");
    }
}

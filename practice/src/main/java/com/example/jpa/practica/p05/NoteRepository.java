package com.example.jpa.practica.p05;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // Cerință: notițele dintr-o categorie, folosind @Query JPQL (nu derived).
    // Adaugă @Query cu :category legat prin @Param, șterge `default` + corpul.

    @Query("select n from Note n where n.category = :category")
    List<Note> findInCategory(@Param("category") String category);


}

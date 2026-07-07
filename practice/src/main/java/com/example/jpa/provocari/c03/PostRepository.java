package com.example.jpa.provocari.c03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    default List<Post> searchByKeyword(String keyword) {
        throw new UnsupportedOperationException("TODO c03");
    }
}

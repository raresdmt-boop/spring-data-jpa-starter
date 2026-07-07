package com.example.jpa.provocari.c04;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Remark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;

    private int likes;

    protected Remark() {
    }

    public Remark(String body, int likes) {
        this.body = body;
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public int getLikes() {
        return likes;
    }
}

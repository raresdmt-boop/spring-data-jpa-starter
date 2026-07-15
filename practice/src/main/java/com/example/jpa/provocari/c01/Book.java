package com.example.jpa.provocari.c01;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private int pages;

    protected Book() {
    }

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }
}

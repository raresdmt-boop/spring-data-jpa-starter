package com.example.jpa.provocari.c01;

public class Book {

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

package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;



    private String genre;

    private double price;

    private int publishedYear;

    protected Book() {
    }

    public Book(String title, String author, String genre, double price, int publishedYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.publishedYear = publishedYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getPublishedYear() {
        return publishedYear;
    }
}

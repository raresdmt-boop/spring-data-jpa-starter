package com.example.bookstore;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO challenge.1: partea "one" a relației Category 1—* Book.
    //   Adaugă @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true).
    private List<Book> books = new ArrayList<>();

    protected Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setCategory(this);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }
}

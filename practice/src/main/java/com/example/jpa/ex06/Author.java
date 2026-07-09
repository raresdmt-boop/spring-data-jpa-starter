package com.example.jpa.ex06;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // TODO ex06.1: mapează partea "one" a relației 1—* dintre Author și Book.
    //   Adaugă @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true).
    //   `mappedBy = "author"` spune că partea care DEȚINE relația (cu FK) e câmpul
    //   `author` din Book; cascade ALL salvează/șterge cărțile odată cu autorul.

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    protected Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
        book.setAuthor(this);
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

package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CatalogRunner implements CommandLineRunner {

    private final BookRepository books;

    public CatalogRunner(BookRepository books) {
        this.books = books;
    }

    @Override
    public void run(String... args) {
        books.deleteAll();
        books.saveAll(List.of(
                new Book("Clean Code", "Robert Martin", "Tech", 180.0, 2008),
                new Book("Effective Java", "Joshua Bloch", "Tech", 220.0, 2018),
                new Book("The Pragmatic Programmer", "Hunt & Thomas", "Tech", 150.0, 1999),
                new Book("Dune", "Frank Herbert", "SF", 90.0, 1965),
                new Book("Foundation", "Isaac Asimov", "SF", 75.0, 1951),
                new Book("Neuromancer", "William Gibson", "SF", 60.0, 1984)));

        System.out.println();
        System.out.println("=== Spring Data JPA — catalog demo ===");
        System.out.println("Total carti in DB (findAll):        " + books.count());

        System.out.println();
        System.out.println("--- Derived query: findByGenre(\"Tech\") ---");
        books.findByGenre("Tech").forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));

        System.out.println();
        System.out.println("--- Derived query: findByAuthorContainingIgnoreCase(\"asimov\") ---");
        books.findByAuthorContainingIgnoreCase("asimov").forEach(b -> System.out.println("  " + b.getTitle()));

        System.out.println();
        System.out.println("--- Numarare: countByGenre(\"SF\") = " + books.countByGenre("SF"));

        System.out.println();
        System.out.println("--- @Query JPQL: findAffordable(<= 100 lei) ---");
        books.findAffordable(100.0).forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));

        System.out.println();
        System.out.println("--- @Query agregare: averagePriceForGenre(\"Tech\") = "
                + books.averagePriceForGenre("Tech") + " lei");

        System.out.println();
        System.out.println("--- Paginare: prima pagina de 2 carti Tech, sortate crescator dupa pret ---");
        Page<Book> page = books.findByGenre("Tech", PageRequest.of(0, 2, Sort.by("price").ascending()));
        System.out.println("  total elemente: " + page.getTotalElements() + ", total pagini: " + page.getTotalPages());
        page.getContent().forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));
        System.out.println();
    }
}

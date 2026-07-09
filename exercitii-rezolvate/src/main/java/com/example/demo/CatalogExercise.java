package com.example.demo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CatalogExercise implements DemoExercise {

    private final BookRepository books;

    public CatalogExercise(BookRepository books) {
        this.books = books;
    }

    @Override
    public String key() {
        return "catalog";
    }

    @Override
    public String title() {
        return "@Entity + derived queries + @Query + paginare (catalog de carti)";
    }

    @Override
    public void run() {
        books.deleteAll();
        books.saveAll(List.of(
                new Book("Clean Code", "Robert Martin", "Tech", 180.0, 2008),
                new Book("Effective Java", "Joshua Bloch", "Tech", 220.0, 2018),
                new Book("The Pragmatic Programmer", "Hunt & Thomas", "Tech", 150.0, 1999),
                new Book("Dune", "Frank Herbert", "SF", 90.0, 1965),
                new Book("Foundation", "Isaac Asimov", "SF", 75.0, 1951),
                new Book("Neuromancer", "William Gibson", "SF", 60.0, 1984)));

        System.out.println("Total carti in DB (count):          " + books.count());

        System.out.println();
        System.out.println("--- derived: findByGenre(\"Tech\") ---");
        books.findByGenre("Tech").forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));

        System.out.println();
        System.out.println("--- derived: findByAuthorContainingIgnoreCase(\"asimov\") ---");
        books.findByAuthorContainingIgnoreCase("asimov").forEach(b -> System.out.println("  " + b.getTitle()));

        System.out.println();
        System.out.println("--- derived: countByGenre(\"SF\") = " + books.countByGenre("SF"));

        System.out.println();
        System.out.println("--- @Query JPQL: findAffordable(<= 100 lei) ---");
        books.findAffordable(100.0).forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));

        System.out.println();
        System.out.println("--- @Query agregare: averagePriceForGenre(\"Tech\") = "
                + books.averagePriceForGenre("Tech") + " lei");

        System.out.println();
        System.out.println("--- paginare: prima pagina de 2 carti Tech, sortate crescator dupa pret ---");
        Page<Book> page = books.findByGenre("Tech", PageRequest.of(0, 2, Sort.by("price").ascending()));
        System.out.println("  total elemente: " + page.getTotalElements() + ", total pagini: " + page.getTotalPages());
        page.getContent().forEach(b -> System.out.println("  " + b.getTitle() + " — " + b.getPrice() + " lei"));

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT * FROM book;");
    }
}

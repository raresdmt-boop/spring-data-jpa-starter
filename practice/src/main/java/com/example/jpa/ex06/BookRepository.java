package com.example.jpa.ex06;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    // TODO ex06.3: cărțile scrise de un autor cu un anumit nume.
    //   Derived query care NAVIGHEAZĂ relația: `findByAuthorName` traversează
    //   Book -> author -> name. Șterge `default` + corpul și lasă semnătura.

    List<Book> findByAuthorName(String name);

    //
//    default List<Book> findByAuthorName(String name) {
//        throw new UnsupportedOperationException("TODO ex06.3: findByAuthorName");
//    }
}

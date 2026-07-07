package com.example.jpa.ex02;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Entitatea Task e deja mapată. Aici exersezi DERIVED QUERIES: Spring Data
// generează SQL-ul din NUMELE metodei. Pentru fiecare TODO: șterge `default`
// și corpul { ... } și lasă doar semnătura abstractă — atât.
public interface TaskRepository extends JpaRepository<Task, Long> {

    // TODO ex02.1: task-urile cu done = valoarea dată.
    //   -> List<Task> findByDone(boolean done);
    default List<Task> findByDone(boolean done) {
        throw new UnsupportedOperationException("TODO ex02.1: findByDone");
    }

    // TODO ex02.2: task-urile cu priority strict mai mare decât valoarea dată.
    //   Cuvântul cheie derivat este `GreaterThan`.
    default List<Task> findByPriorityGreaterThan(int priority) {
        throw new UnsupportedOperationException("TODO ex02.2: findByPriorityGreaterThan");
    }

    // TODO ex02.3: task-urile al căror titlu conține fragmentul, indiferent de
    //   majuscule. Cuvinte cheie derivate: `Containing` + `IgnoreCase`.
    default List<Task> findByTitleContainingIgnoreCase(String fragment) {
        throw new UnsupportedOperationException("TODO ex02.3: findByTitleContainingIgnoreCase");
    }
}

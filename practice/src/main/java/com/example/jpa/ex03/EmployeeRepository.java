package com.example.jpa.ex03;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// Derived queries mai avansate: sortare, numărare, existență, potrivire parțială.
// Pentru fiecare TODO: șterge `default` + corpul și lasă doar semnătura.
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // TODO ex03.1: angajații dintr-un departament, ordonați descrescător după salariu.
    //   Cuvinte cheie: `OrderBy` + `Desc`.
    default List<Employee> findByDepartmentOrderBySalaryDesc(String department) {
        throw new UnsupportedOperationException("TODO ex03.1: findByDepartmentOrderBySalaryDesc");
    }

    // TODO ex03.2: câți angajați sunt activi. Întoarce `long`, prefix `countBy`,
    //   condiție pe boolean adevărat: `ActiveTrue`.
    default long countByActiveTrue() {
        throw new UnsupportedOperationException("TODO ex03.2: countByActiveTrue");
    }

    // TODO ex03.3: există vreun angajat cu emailul dat. Întoarce `boolean`, prefix `existsBy`.
    default boolean existsByEmail(String email) {
        throw new UnsupportedOperationException("TODO ex03.3: existsByEmail");
    }

    // TODO ex03.4: angajații al căror nume de familie conține fragmentul (case-insensitive).
    default List<Employee> findByLastNameContainingIgnoreCase(String fragment) {
        throw new UnsupportedOperationException("TODO ex03.4: findByLastNameContainingIgnoreCase");
    }
}

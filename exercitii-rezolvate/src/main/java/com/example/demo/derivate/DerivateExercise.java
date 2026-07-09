package com.example.demo.derivate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.DemoExercise;

@Component
public class DerivateExercise implements DemoExercise {

    private final EmployeeRepository employees;

    public DerivateExercise(EmployeeRepository employees) {
        this.employees = employees;
    }

    @Override
    public String key() {
        return "derivate";
    }

    @Override
    public String title() {
        return "derived queries (OrderBy, GreaterThan, Containing, Top, countBy, existsBy)";
    }

    @Override
    public void run() {
        employees.deleteAll();
        employees.saveAll(List.of(
                new Employee("Ana", "Popescu", "IT", 9000, true, "ana@firma.ro"),
                new Employee("Bogdan", "Ionescu", "IT", 12000, true, "bogdan@firma.ro"),
                new Employee("Cristina", "Marinescu", "IT", 7000, false, "cristina@firma.ro"),
                new Employee("Dan", "Popa", "HR", 6000, true, "dan@firma.ro"),
                new Employee("Elena", "Popescu", "HR", 8000, false, "elena@firma.ro"),
                new Employee("Florin", "Georgescu", "Sales", 15000, true, "florin@firma.ro")));

        System.out.println("Total angajati in DB (count): " + employees.count());

        System.out.println();
        System.out.println("--- findByDepartmentOrderBySalaryDesc(\"IT\") ---");
        employees.findByDepartmentOrderBySalaryDesc("IT")
                .forEach(e -> System.out.println("  " + e.getFirstName() + " " + e.getLastName() + " — " + e.getSalary()));

        System.out.println();
        System.out.println("--- findByDepartmentAndActiveTrue(\"IT\") ---");
        employees.findByDepartmentAndActiveTrue("IT")
                .forEach(e -> System.out.println("  " + e.getFirstName() + " (activ)"));

        System.out.println();
        System.out.println("--- findBySalaryGreaterThan(8000) ---");
        employees.findBySalaryGreaterThan(8000)
                .forEach(e -> System.out.println("  " + e.getFirstName() + " — " + e.getSalary()));

        System.out.println();
        System.out.println("--- findByLastNameContainingIgnoreCase(\"popescu\") ---");
        employees.findByLastNameContainingIgnoreCase("popescu")
                .forEach(e -> System.out.println("  " + e.getFirstName() + " " + e.getLastName()));

        System.out.println();
        System.out.println("--- findTop3ByOrderBySalaryDesc() (cei mai bine platiti 3) ---");
        employees.findTop3ByOrderBySalaryDesc()
                .forEach(e -> System.out.println("  " + e.getFirstName() + " — " + e.getSalary()));

        System.out.println();
        System.out.println("--- countByActiveTrue() = " + employees.countByActiveTrue());
        System.out.println("--- existsByEmail(\"ana@firma.ro\") = " + employees.existsByEmail("ana@firma.ro"));

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT * FROM employee ORDER BY salary DESC;");
    }
}

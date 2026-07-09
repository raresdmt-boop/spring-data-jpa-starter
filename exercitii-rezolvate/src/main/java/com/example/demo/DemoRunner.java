package com.example.demo;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements CommandLineRunner {

    private final Map<String, DemoExercise> exercises = new LinkedHashMap<>();

    public DemoRunner(List<DemoExercise> found) {
        found.stream()
                .sorted(Comparator.comparing(DemoExercise::key))
                .forEach(e -> exercises.put(e.key(), e));
    }

    @Override
    public void run(String... args) {
        DemoExercise selected = args.length > 0 ? exercises.get(args[0]) : null;

        if (selected == null) {
            printMenu(args.length > 0 ? args[0] : null);
            return;
        }

        System.out.println();
        System.out.println("========================================================");
        System.out.println("  " + selected.key() + " — " + selected.title());
        System.out.println("========================================================");
        selected.run();
        System.out.println();
    }

    private void printMenu(String unknown) {
        System.out.println();
        if (unknown != null) {
            System.out.println("Exercitiu necunoscut: \"" + unknown + "\".");
        }
        System.out.println("Alege ce exercitiu rulezi (datele raman in MySQL, le inspectezi in DB):");
        System.out.println();
        exercises.forEach((k, e) ->
                System.out.println("  ./mvnw -pl exercitii-rezolvate spring-boot:run -Dspring-boot.run.arguments="
                        + k + "\n      -> " + e.title() + "\n"));
    }
}

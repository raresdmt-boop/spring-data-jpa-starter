package com.example.demo.unulaunu;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoExercise;

@Component
public class UnuLaUnuExercise implements DemoExercise {

    private final CitizenRepository citizens;

    public UnuLaUnuExercise(CitizenRepository citizens) {
        this.citizens = citizens;
    }

    @Override
    public String key() {
        return "unulaunu";
    }

    @Override
    public String title() {
        return "@OneToOne (1-1): un cetatean are un pasaport";
    }

    @Override
    @Transactional
    public void run() {
        citizens.deleteAll();

        Citizen ana = new Citizen("Ana Popescu");
        ana.setPassport(new Passport("RO-123456"));

        Citizen bogdan = new Citizen("Bogdan Ionescu");
        bogdan.setPassport(new Passport("RO-987654"));

        citizens.save(ana);
        citizens.save(bogdan);

        System.out.println("Cascade ALL: am salvat cetateanul, pasaportul s-a salvat automat.");
        System.out.println("Cetateni in DB: " + citizens.count());

        System.out.println();
        System.out.println("--- navigare 1->1 : cetatean si pasaportul lui ---");
        citizens.findAll().forEach(c ->
                System.out.println("  " + c.getName() + " -> pasaport " + c.getPassport().getCode()));

        System.out.println();
        System.out.println("--- derived peste relatie: findByPassportCode(\"RO-123456\") ---");
        citizens.findByPassportCode("RO-123456")
                .ifPresent(c -> System.out.println("  gasit: " + c.getName()));

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT * FROM citizen;  (vezi coloana FK passport_id)  apoi  SELECT * FROM passport;");
    }
}

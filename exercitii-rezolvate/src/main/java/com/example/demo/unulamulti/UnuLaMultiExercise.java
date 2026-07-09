package com.example.demo.unulamulti;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.DemoExercise;

@Component
public class UnuLaMultiExercise implements DemoExercise {

    private final TeamRepository teams;

    private final PlayerRepository players;

    public UnuLaMultiExercise(TeamRepository teams, PlayerRepository players) {
        this.teams = teams;
        this.players = players;
    }

    @Override
    public String key() {
        return "unulamulti";
    }

    @Override
    public String title() {
        return "@OneToMany / @ManyToOne (1-*): o echipa are mai multi jucatori";
    }

    @Override
    @Transactional
    public void run() {
        players.deleteAll();
        teams.deleteAll();

        Team steaua = new Team("Steaua");
        steaua.addPlayer(new Player("Ionut", "Portar"));
        steaua.addPlayer(new Player("Marius", "Fundas"));
        steaua.addPlayer(new Player("Vlad", "Atacant"));

        Team dinamo = new Team("Dinamo");
        dinamo.addPlayer(new Player("Andrei", "Mijlocas"));
        dinamo.addPlayer(new Player("Cosmin", "Atacant"));

        teams.save(steaua);
        teams.save(dinamo);

        System.out.println("Cascade ALL: am salvat doar echipele, jucatorii s-au salvat automat.");
        System.out.println("Echipe: " + teams.count() + ", jucatori: " + players.count());

        System.out.println();
        System.out.println("--- navigare 1->* : echipa cu jucatorii ei ---");
        teams.findAll().forEach(t -> {
            System.out.println("  " + t.getName() + ":");
            t.getPlayers().forEach(p -> System.out.println("     - " + p.getName() + " (" + p.getPosition() + ")"));
        });

        System.out.println();
        System.out.println("--- derived peste relatie: findByTeamName(\"Steaua\") ---");
        players.findByTeamName("Steaua").forEach(p -> System.out.println("  " + p.getName()));

        System.out.println();
        System.out.println("--- findByPosition(\"Atacant\") ---");
        players.findByPosition("Atacant")
                .forEach(p -> System.out.println("  " + p.getName() + " (" + p.getTeam().getName() + ")"));

        System.out.println();
        System.out.println("Inspecteaza in DB:  SELECT * FROM team;  apoi  SELECT * FROM player;  (vezi coloana FK team_id)");
    }
}

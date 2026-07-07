package com.example.jpa.provocari.c10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ContextConfiguration(classes = C10TeamOrphanRemovalTest.Config.class)
class C10TeamOrphanRemovalTest {

    @Configuration
    @EntityScan(basePackageClasses = Team.class)
    @EnableJpaRepositories(basePackageClasses = TeamRepository.class)
    static class Config {
    }

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void removingAthleteFromTeamDeletesItFromDatabase() {
        // Arrange
        Team team = new Team("Vipers");
        Athlete ana = new Athlete("Ana");
        Athlete ion = new Athlete("Ion");
        team.addAthlete(ana);
        team.addAthlete(ion);
        Team saved = teamRepository.save(team);
        entityManager.flush();

        // Act
        Team reloaded = teamRepository.findById(saved.getId()).orElseThrow();
        reloaded.removeAthlete(reloaded.getAthletes().get(0));
        teamRepository.save(reloaded);
        entityManager.flush();

        // Assert
        assertEquals(1, athleteRepository.count());
    }
}

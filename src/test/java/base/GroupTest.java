package base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {
    Group group;
    @BeforeEach
    void setUp() {
        group = new Group();
        group.addTeam(new FootballClub("test1", "testN1"));
        group.addTeam(new FootballClub("test2", "testN2"));
        group.addTeam(new FootballClub("test3", "testN3"));
        group.addTeam(new FootballClub("test4", "testN4"));

    }

    @Test
    void getGroupTeams() {
        assertNotNull(group.getGroupTeams());
        assertEquals(group.getGroupTeams().size(), 4);
    }

    @Test
    void generateAndGetGroupMatches() {
        group.generateMatches();
        assertNotNull(group.getGroupMatches());
        assertEquals(group.getGroupMatches().size(), 6);
    }

    @Test
    void addTeam() {
        group = new Group();
        group.addTeam(new FootballClub("test1", "testN1"));
        assertEquals(group.getGroupTeams().size(), 1);
    }

}
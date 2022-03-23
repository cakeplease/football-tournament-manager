package base;

import controller.GroupController;
import model.FootballClubsFromFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TournamentManagerTest {
    TournamentManager tr;
    @BeforeEach
    void setUp() {
        tr = new TournamentManager(new GroupController());
        GroupController gc = tr.getGroupController();
        gc.addAll(FootballClubsFromFile.readFromFile());
        gc.generateGroups();

        gc.getGroups().forEach(e->{
            try{
                e.generateMatches();
                e.testSimulateAllMatches();
                e.endGrupe();
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });

    }

    @Test
    void getRoundOf32A() {
        tr.generateRoundOf32();
        assertNotNull(tr.getRoundOf32A());
    }

    @Test
    void getRoundOf32B() {
        tr.generateRoundOf32();
        assertNotNull(tr.getRoundOf32B());
    }

    @Test
    void getRoundOf16A() {
    }

    @Test
    void getRoundOf16B() {
    }

    @Test
    void getQuarterFinalsA() {
    }

    @Test
    void getQuarterFinalsB() {
    }

    @Test
    void getSemifinalsA() {
    }

    @Test
    void getSemifinalsB() {
    }

    @Test
    void listGroupMatches() {
    }

    @Test
    void generateRoundOf32() {
    }

    @Test
    void generateRoundOf16() {
    }

    @Test
    void generateQuarterFinals() {
    }

    @Test
    void generateSemifinals() {
    }

    @Test
    void generateFinalA() {
    }

    @Test
    void generateFinalB() {
    }
}
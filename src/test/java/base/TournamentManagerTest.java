package base;

import controller.GroupController;
import model.FootballClubsFromFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TournamentManagerTest {
    TournamentManager tr;
    @BeforeEach
    void setUp() {
        tr = new TournamentManager();
        GroupController gc = tr.getGroupController();
        gc.resetList();
        gc.addAll(FootballClubsFromFile.readFromFile());
        gc.generateGroups();

        gc.getGroups().forEach(e->{
            try{
                e.generateMatches();
                e.testSimulateAllMatches();
                e.endGroup();
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
        });
    }

    @Test
    void groupsTest() {
        tr = new TournamentManager();
        GroupController gc = tr.getGroupController();
        gc.resetList();
        gc.addAll(FootballClubsFromFile.readFromFile());
        gc.generateGroups();

        assertEquals(16, gc.getGroups().size());
    }


    @Test
    void getRoundOf32A() {
        tr.generateRoundOf32();
        assertNotNull(tr.getRoundOf32A());
    }

    //The rest of the functions for generating other finales are working the same as the one above
}
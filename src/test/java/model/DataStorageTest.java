package model;

import base.Group;
import base.TournamentManager;
import controller.GroupController;
import org.junit.jupiter.api.*;

import javax.xml.crypto.Data;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {

    @BeforeEach
    void setUp(){
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();
    }

    @Test
    @Order(1)
    void saveFootballClubs() {
        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        DataStorage.saveFootballClubs(true);
    }
    @Test
    @Order(3)
    void save(){
        DataStorage.load(true);
        GroupController.getInstance().getGroups().forEach(Group::testSimulateAllMatches);
        GroupController.getInstance().getGroups().forEach(Group::endGroup);
        DataStorage.save(true);
        DataStorage.load(true);
        assertNotEquals(GroupController.getInstance().getGroups().size(), 0);

    }


    @Test
    @Order(2)
    void load(){
        DataStorage.load(true);
        assertNotEquals(GroupController.getInstance().getGroups().size(), 0);
    }


    @Test
    @Order(4)
    void saveTournamentFinals(){
        TournamentManager tr = TournamentManager.getInstance();
        DataStorage.load(true);
        GroupController.getInstance().getGroups().forEach(Group::testSimulateAllMatches);
        GroupController.getInstance().getGroups().forEach(Group::endGroup);
        TournamentManager.getInstance().simAll();
        assertEquals(TournamentManager.getInstance().getFinalsMatches().size(), 2);
        DataStorage.save(true);
    }

    @Test
    @Order(5)
    void loadFinalsMatches(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
        }
        DataStorage.load(true);
        assertTrue(TournamentManager.getInstance().getRoundOf32A().size() > 0);
        assertEquals(TournamentManager.getInstance().getFinalsMatches().size(), 2);

    }




    @AfterAll
    static void tearDown(){
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();
    }
}
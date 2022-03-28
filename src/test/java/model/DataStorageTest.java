package model;

import controller.GroupController;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {

    @BeforeAll
    static void beforeAllSetup(){
        try {
            FileWriter fw = new FileWriter(String.valueOf(DataStorage.getFootballClubsPath(true)), true);
        }catch (IOException e){
            assert false;
        }
    }

    @BeforeEach
    void setUp(){
        GroupController.getInstance().resetList();
    }

    @Test
    @Order(1)
    void saveFootballClubs() {
        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        DataStorage.saveFootballClubs(true);
        assertNotNull(DataHandler.readFromFile(DataStorage.getFootballClubsPath(true)));
    }

    @Test
    @Order(2)
    void loadFootballClubsFromFile(){
        assertEquals(GroupController.getInstance().getFootballClubs().size(), 0);
        DataStorage.loadFootballClubsFromFile(true);
        assertEquals(GroupController.getInstance().getFootballClubs().size(), 64);
    }
    @AfterAll
    static void tearDown(){
        GroupController.getInstance().resetList();
    }
}
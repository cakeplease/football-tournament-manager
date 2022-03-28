package model;

import controller.GroupController;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {
    @BeforeAll
    static void beforeSetup(){
        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        GroupController.getInstance().generateGroups();
        DataStorage.save(true);
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
    }
    @Test
    @Order(3)
    void save(){
        DataStorage.load(true);
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


    @AfterAll
    static void tearDown(){
        GroupController.getInstance().resetList();
    }
}
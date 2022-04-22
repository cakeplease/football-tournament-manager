package model;

import base.Group;
import base.TournamentManager;
import controller.GroupController;
import org.junit.jupiter.api.*;


import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {

    void resetAllDataAndDeleteFiles(){
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();
        File footballClubs = new File("src/main/resources/data/football-clubs.csv");
        File groupsData = new File("src/main/resources/data/groups-data.csv");
        File groupsMatches = new File("src/main/resources/data/groups-matches.csv");
        File tournamentFinales = new File("src/main/resources/data/tournament-finales.csv");

        footballClubs.delete();
        groupsData.delete();
        groupsMatches.delete();
        tournamentFinales.delete();
    }

    @Test
    void loadAll16GroupsFromFile() {
        resetAllDataAndDeleteFiles();
        DataStorage.loadTestData();
        assertEquals(GroupController.getInstance().getGroups().size(), 16);
    }

    @Test
    void saveFootballClubs() {
        resetAllDataAndDeleteFiles();

        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        DataStorage.saveFootballClubs();
    }
    @Test
    void save(){
        DataStorage.loadTestData();
       /* GroupController.getInstance().getGroups().forEach(Group::testSimulateAllMatches);
        GroupController.getInstance().getGroups().forEach(Group::endGroup);*/
        DataStorage.save();
        assertNotEquals(GroupController.getInstance().getGroups().size(), 0);
    }

    //TODO make a file with test finales data
   /* @Test
    @Order(4)
    void saveTournamentFinales(){
        TournamentManager tr = TournamentManager.getInstance();
        DataStorage.loadTestData();
        GroupController.getInstance().getGroups().forEach(Group::testSimulateAllMatches);
        GroupController.getInstance().getGroups().forEach(Group::endGroup);
        TournamentManager.getInstance().simAll();
        assertEquals(TournamentManager.getInstance().getFinalsMatches().size(), 2);
        DataStorage.save();
    }*/

    /*@Test
    void loadFinalesMatches(){
        resetAllDataAndDeleteFiles();
        *//*try {
            Thread.sleep(2000);
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }*//*

        DataStorage.loadTestData();
        assertTrue(TournamentManager.getInstance().getRoundOf32A().size() > 0);
        assertEquals(TournamentManager.getInstance().getFinalsMatches().size(), 2);

    }*/

}
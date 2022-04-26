package model;

import base.Group;
import base.TournamentManager;
import controller.GroupController;
import org.junit.jupiter.api.*;


import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageTest {

    /**
     * method for reseting and deleating all files in test
     */
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

    /**
     * method for simming all matches and saving it to file for test data
     * this method is purly for testing and presentation of prodcut
     */
    void makeTestData(){
        //reset all files
        resetAllDataAndDeleteFiles();

        //generate and sim everything
        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        GroupController.getInstance().generateGroups();
        GroupController.getInstance().getGroups().forEach(Group::generateMatches);
        GroupController.getInstance().getGroups().forEach(Group::testSimulateAllMatches);
        GroupController.getInstance().getGroups().forEach(Group::endGroup);
        TournamentManager.getInstance().simAll();

        //save all
        DataStorage.save();
    }

    @Test
    void loadAll16GroupsFromFile() {
        resetAllDataAndDeleteFiles();

        //first load all data so it dose not corrupt then we can check the methods
        DataStorage.loadTestData();

        //check if grupes list resets after eatch load and consistant
        assertEquals(GroupController.getInstance().getGroups().size(), 16);

        Group g1 = GroupController.getInstance().getGroups().get(0);

        DataStorage.loadTestData();
        assertEquals(GroupController.getInstance().getGroups().size(), 16);

        Group g2 = GroupController.getInstance().getGroups().get(0);

        assertEquals(g1.getGroupNumber(), g2.getGroupNumber());
        assertEquals(g1.getGroupTeams().get(0), g2.getGroupTeams().get(0));


    }

    @Test
    void saveFootballClubs() {
        resetAllDataAndDeleteFiles();

        GroupController.getInstance().addAll(FootballClubsFromFile.readFromFile());
        DataStorage.save();

        assert(GroupController.getInstance().getFootballClubs().size() > 0);
        assert(new File("src/main/resources/data/football-clubs.csv")).exists();

    }

    @Test
    void saveAllData(){
        //rests all lists and files
        resetAllDataAndDeleteFiles();
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();

        //load data
        DataStorage.loadTestData();

        //save the data
        DataStorage.save();

        //check if saved
        assert(new File("src/main/resources/data/football-clubs.csv")).exists();
        assert(new File("src/main/resources/data/groups-data.csv")).exists();
        assert(new File("src/main/resources/data/groups-matches.csv")).exists();
        assert(new File("src/main/resources/data/tournament-finales.csv")).exists();
    }




   @Test
    void saveTournamentFinales(){
       resetAllDataAndDeleteFiles();

       DataStorage.save();

       assert(GroupController.getInstance().getFootballClubs().size() > 0);
       assert(new File("src/main/resources/data/football-clubs.csv")).exists();
    }

    @Test
    void loadFinalesMatches(){
        resetAllDataAndDeleteFiles();

        DataStorage.loadTestData();
        assertTrue(TournamentManager.getInstance().getRoundOf32A().size() > 0);
        assertEquals(TournamentManager.getInstance().getFinalA().size(), 1);

    }

}
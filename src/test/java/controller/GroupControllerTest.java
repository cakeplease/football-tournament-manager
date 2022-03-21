package controller;

import base.FootballClub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {

    @Test
    @DisplayName("Test to check if FootballClub is saved to csv-file")
    public void checkIfFootballClubIsSavedToCsv() {
        GroupController gc = new GroupController();
        gc.addFootballClub("Test", "Norway");
        gc.saveFootballClubs();
    }

    /* TODO: Add test when all group data is added

     @Test
     @DisplayName("Test to check if Groups are saved to csv-file")
    public void checkIfGroupsAreSavedToCsv() {
         GroupController gc = new GroupController();
         gc.addFootballClub("Test", "Norway");
         gc.generateGroups();
         gc.saveGroups();
    }

     */
}
package controller;

import base.FootballClub;
import base.Group;
import model.FootballClubsFromFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {

    @Test
    void addFootballClubTest() {
        GroupController groupController = GroupController.getInstance();
        groupController.addFootballClub("Daniels lag", "Norge");
        assertEquals(new FootballClub("Daniels lag", "Norge"), groupController.getFootballClubs().get(0));
    }

    @Test
    void addAllTest() {
        GroupController groupController = GroupController.getInstance();
        ArrayList<FootballClub> footballClubs = FootballClubsFromFile.readFromFile();
        groupController.addAll(footballClubs);
        assertEquals(footballClubs, groupController.getFootballClubs());
        assertThrows(NullPointerException.class, () -> {groupController.addAll(null);});
    }

    @Test
    void illegalArgumentTest() {
        GroupController groupController = GroupController.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("", "Norge");});
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("Daniels lag", "");});
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("", "");});
    }


    @Test
    void generateGroupsTest() {
        ArrayList<FootballClub> footballClubs = FootballClubsFromFile.readFromFile();
        GroupController groupController = GroupController.getInstance();
        groupController.addAll(footballClubs);
        assertEquals(64, groupController.getFootballClubs().size());
        assertTrue(groupController.generateGroups());
    }
}
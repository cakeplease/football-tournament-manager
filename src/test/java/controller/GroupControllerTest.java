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
        groupController.resetList();
        groupController.addFootballClub("Daniels lag", "Norge");
        assertEquals(new FootballClub("Daniels lag", "Norge"), groupController.getFootballClubs().get(0));
    }

    @Test
    void addAllTest() {
        GroupController groupController = GroupController.getInstance();
        ArrayList<FootballClub> footballClubs = FootballClubsFromFile.readFromFile();
        assertThrows(RuntimeException.class, () -> groupController.addAll(footballClubs));
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
        groupController.resetList();
        groupController.addAll(footballClubs);
        groupController.addAll(footballClubs);
        assertThrows(RuntimeException.class, ()-> groupController.addFootballClub("test1", "test2"));
        assertTrue(groupController.generateGroups());
    }
}
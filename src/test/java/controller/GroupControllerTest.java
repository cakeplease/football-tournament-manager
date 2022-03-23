package controller;

import base.FootballClub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupControllerTest {

    @Test
    void addFootballClubTest() {
        GroupController groupController = new GroupController();
        groupController.addFootballClub("Daniels lag", "Norge");
        assertEquals(new FootballClub("Daniels lag", "Norge"), groupController.getFootballClubs().get(0));
    }

    @Test
    void illegalArgumentTest() {
        GroupController groupController = new GroupController();
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("", "Norge");});
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("Daniels lag", "");});
        assertThrows(IllegalArgumentException.class, () -> {groupController.addFootballClub("", "");});
    }
}
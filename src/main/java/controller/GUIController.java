package controller;

import base.FootballClub;
import model.FootballClubsFromFile;

import java.util.ArrayList;

public class GUIController {

    /**
     * Adds team based on params from the form
     * @param name name of the team
     * @param nationality nationality of the team
     */
    //TODO change to boolean on successful add
    public static void addTeam(String name, String nationality) {
        //TODO add team handler
        System.out.println("Team added");
    }

    public static void generateGroups() {
        GroupController groupController = GroupController.getInstance();
        ArrayList<FootballClub> footballClubs = FootballClubsFromFile.readFromFile();
        groupController.addAll(footballClubs);
        groupController.generateGroups();
        System.out.println(groupController.getGroups());

    }
}

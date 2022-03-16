package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.*;
import java.lang.System;

/**
 * Simple client for testing purposes before we get into GUI
 */
public class SimpleClient {
    Scanner input = new Scanner(System.in);
    GroupController GroupController = new GroupController();
    //TournamentManager TournamentManager = new TournamentManager();

    boolean hasStarted = false;

    /**
     * Shows menu with different choices
     */
    public void showMenu() {

        if (!hasStarted) {
            testData();
            hasStarted = true;
        }

        System.out.println("Type 1 to register a football team.");
        System.out.println("Type 2 to show all teams.");
        System.out.println("Type 3 to generate groups.");
        System.out.println("Type 4 to show all groups.");
        System.out.println("Type 6 to quit.");

        int menuInput = input.nextInt();

        switch (menuInput) {
            case 1:
                registerFootballClub();
                break;
            case 2:
                showTeams();
                break;
            case 3:
                generateGroups();
                break;
            case 4:
                showGroups();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong input number.");
        }
    }

    private void showTeams() {
        ArrayList<FootballClub> footballClubs = GroupController.getFootballClubs();
        for (FootballClub footballclub: footballClubs) {
            System.out.println(footballclub.toString());
        }
    }

    private void generateGroups() {
        //TODO generate group not added yet in GroupController
    }

    private void showGroups() {
        //TODO
    }

    /**
     * Registers football club
     */
    private void registerFootballClub() {
        System.out.print("Name: ");
        String name = input.next();

        System.out.print("Nationality: ");
        String nationality = input.next();

        input.nextLine();

        GroupController.addFootballClub(name, nationality);
    }

    /**
     * Test data
     */
    private void testData() {
        GroupController.addFootballClub("Rosenborg", "Norway");
        GroupController.addFootballClub("Byneset", "Norway");
        GroupController.addFootballClub("Ajax", "England");
        GroupController.addFootballClub("Lechia Gdansk", "Poland");
    }

    /**
     * Runs the main loop
     * @param args
     */
    public static void main(String[] args) {
        SimpleClient SimpleClient = new SimpleClient();

        while (true) {
            SimpleClient.showMenu();
        }
    }

}
package controller;

import base.FootballClub;
import base.Group;
import base.Match;
import base.TournamentManager;
import model.DataStorage;
import model.FootballClubsFromFile;
import java.util.ArrayList;

/**
 * GUIController user interactions
 * Controller part of the system
 * //TODO split up into smaller controllers
 */
public class GUIController {
    /**
     * Adds team based on params from the form
     * @param name name of the team
     * @param nationality nationality of the team
     * @return validation string (to print)
     */
    public static String addTeam(String name, String nationality) {
        GroupController groupController = GroupController.getInstance();
        try {
            groupController.addFootballClub(name, nationality);
        } catch (RuntimeException e) {
            return e.getMessage();
        }

        return "Team added.";
    }

    /**
     * Edits an existing match
     * @param team1 The first football team playing the match
     * @param team2 The second football team playing the match
     * @param time The updated time of the match, can be left blank
     * @param date The updated date of the match, can be left blank
     * @param fieldNr The updated field number where the match takes place, can be left blank
     * @param score1 The updated score of team 1, can be left blank
     * @param score2 The updated score of team 2, can be left blank
     * @return true if the match got edited successfully, false otherwise
     */
    public static boolean editMatch(FootballClub team1, FootballClub team2, String time, String date, String fieldNr, String score1, String score2){
        if (team1 == null || team2 == null) {
            return false;
        }

        TournamentManager tournamentManager = TournamentManager.getInstance();
        GroupController groupController = GroupController.getInstance();

        ArrayList<Match> allMatches = new ArrayList<>();
        for (Group group : groupController.getGroups()) {
            allMatches.addAll(group.getGroupMatches());
        }

        // Adds all matches to an arraylist
        allMatches.addAll(tournamentManager.getRoundOf32A());
        allMatches.addAll(tournamentManager.getRoundOf32B());
        allMatches.addAll(tournamentManager.getRoundOf16A());
        allMatches.addAll(tournamentManager.getRoundOf16B());
        allMatches.addAll(tournamentManager.getQuarterFinalsA());
        allMatches.addAll(tournamentManager.getQuarterFinalsB());
        allMatches.addAll(tournamentManager.getSemifinalsA());
        allMatches.addAll(tournamentManager.getSemifinalsB());
        allMatches.addAll(tournamentManager.getFinalA());
        allMatches.addAll(tournamentManager.getFinalB());

        // Search for the match in group matches
        for (Match m : allMatches) {
            if ((team1.equals(m.getFootballClub1())) && (team2.equals(m.getFootballClub2()))){
                if (time.equals("")) time = m.getTime();
                if (date.equals("")) date = m.getDate();
                if (fieldNr.equals("")) fieldNr = String.valueOf(m.getFieldNr());
                if (score1.equals("")) score1 = String.valueOf(m.getScore1());
                if (score2.equals("")) score2 = String.valueOf(m.getScore2());

                m.setTime(time);
                m.setDate(date);
                m.setFieldNr(Integer.parseInt(fieldNr));
                m.setScore1(Integer.parseInt(score1));
                m.setScore2(Integer.parseInt(score2));

                saveFinalesToFile();
                saveGroupMatchesToFile();

                return true;
            }
        }

        return false;
    }

    /**
     * Check if all matches have winner
     * @param matches Match arraylist to check
     * @return true or false
     */
    public static boolean allMatchesHaveWinner(ArrayList<Match> matches) {
        if (matches.isEmpty()) {
            return false;
        }

        return matches.stream().allMatch(e -> e.getWinner() != null);
    }

    /**
     * Generate groups
     * @return boolean validation if groups was generated
     */
    public static boolean generateGroups() {
        GroupController groupController = GroupController.getInstance();

        return groupController.generateGroups();

    }

    /**
     * Save finales to a file
     */
    public static void saveFinalesToFile() {
        DataStorage.save();
    }

    /**
     * Save group matches to file
     */
    public static void saveGroupMatchesToFile() {
        DataStorage.save();
    }

    /**
     * Save groups
     */
    public static void saveGroups() {
        DataStorage.save();
    }

    /**
     * Loads test data from /resources/test_data
     */
    public static void loadTestData() {
        DataStorage.loadTestData();
    }

}

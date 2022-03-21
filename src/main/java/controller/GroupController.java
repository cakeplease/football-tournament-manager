package controller;

import base.FootballClub;
import base.Group;
import model.DataHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 * GroupController class that stores football clubs and generates groups.
 */
public class GroupController {
    private ArrayList<FootballClub> footballClubs;
    private ArrayList<Group> groups;

    /**
     * GroupController constructor
     */
    public GroupController() {
        this.footballClubs = new ArrayList<>(64);
        this.groups = new ArrayList<>(16);
    }

    public ArrayList<FootballClub> getFootballClubs() {
        return footballClubs;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void addFootballClub(String name, String nationality) {
        FootballClub team = new FootballClub(name, nationality);
        this.footballClubs.add(team);
    }

    public boolean generateGroups(){
        if (this.footballClubs.size() != 64) return false;
        ArrayList<FootballClub> norwegianClubs = new ArrayList<>();
        ArrayList<FootballClub> internationalClubs = new ArrayList<>();
        for (FootballClub f : this.footballClubs) {
            if (f.getNationality().equalsIgnoreCase("norge") || f.getNationality().equalsIgnoreCase("norway")) {
                norwegianClubs.add(f);
            } else {
                internationalClubs.add(f);
            }
            //randomizes both lists
            Collections.shuffle(norwegianClubs);
            Collections.shuffle(internationalClubs);

            for(int i = 0; i < 16; i++){
                this.groups.add(new Group());
                groups.get(i).addTeam(internationalClubs.get(i));
                    for (int k = 0; k < 3; k++){
                        groups.get(i).addTeam(norwegianClubs.get(k +(3*i)));
                    }
            }
        }
        return true;
        }

    /**
     * Method for saving football clubs to csv-file
     * Loops through the football clubs and adds the data to the cvs-file (path)
     */
    public void saveFootballClubs() {
        StringBuilder data = new StringBuilder();
        for (FootballClub fc : footballClubs) {
            data.append(fc.getCsvFormat());
        }
        Path path = Paths.get("src/main/resources/data/FootballClubs.csv");
        DataHandler.saveToFile(data.toString(), path);
    }

    /**
     * Method for saving groups to csv-file
     * Loops through groups and adds each groups data to the cvs-file (path)
     * //TODO: check format of info
     */
    public void saveGroups() {
        StringBuilder data = new StringBuilder();
        for (Group group : groups) {
            data.append(group.getCsvFormatTeams());
        }
            Path path = Paths.get("src/main/resources/data/GeneratedGroups.csv");
            DataHandler.saveToFile(data.toString(), path);
    }

    /**
     * Method for saving matches to csv-file
     * Loops through groups and adds the match history to a cvs-file
     * //TODO: check format of info
     */
    public void saveMatches() {
        StringBuilder data = new StringBuilder();
        for (Group group : groups) {
            data.append(group.getCsvFormatMatches());
        }
        Path path = Paths.get("src/main/resources/data/Matches.csv");
        DataHandler.saveToFile(data.toString(), path);
    }

    @Override
    public String toString() {
        return "GroupController{" +
                "footballClubs=" + footballClubs +
                '}';
    }
}

package controller;

import base.FootballClub;
import base.Group;
import java.util.ArrayList;
import java.util.Collections;

/**
 * GroupController class that stores football clubs and generates groups.
 */
public class GroupController {
    private ArrayList<FootballClub> footballClubs;
    private ArrayList<Group> groups;

    private static GroupController groupController = new GroupController();

    /**
     * GroupController constructor
     */
    private GroupController() {
        this.footballClubs = new ArrayList<>(64);
        this.groups = new ArrayList<>(16);
    }

    public static GroupController getInstance() {
        return groupController;
    }

    /**
     * Method for getting the clubs that have been added
     * @return the list of football clubs
     */
    public ArrayList<FootballClub> getFootballClubs() {
        return footballClubs;
    }

    /**
     * Method for getting the groups that have been created
     * @return a list of the groups
     */
    public ArrayList<Group> getGroups() {
        return groups;
    }

    /**
     * Method for adding a football club
     * @param name Name of the football club
     * @param nationality Nationality of the football club (Either norwegian or foreign)
     * @throws RuntimeException if more then 64 teams, or no name or nationality is given
     */
    public void addFootballClub(String name, String nationality) throws RuntimeException {
        if (name.isBlank() || nationality.isBlank()) {
            throw new IllegalArgumentException("The name and nationality of the football club can not be blank.");
        }
        FootballClub team = new FootballClub(name, nationality);
        if (!this.footballClubs.contains(team)) {
            if (this.footballClubs.size() >= 64) {
                throw new RuntimeException("More than 64 teams cannot be added.");
            }
            this.footballClubs.add(team);
        }
    }

    /**
     * Method for adding a list of football clubs to the groupcontroller
     * @param footballClubsToAdd a list of football clubs
     * @throws RuntimeException if trying to add more then 64 teams total
     */
    public void addAll(ArrayList<FootballClub> footballClubsToAdd) throws RuntimeException{
        if (footballClubsToAdd == null){
            throw new NullPointerException("The footballclubs cannot be null!");
        }
        for (FootballClub footballClub: footballClubsToAdd){
            if (!this.footballClubs.contains(footballClub)){
                if (footballClubs.size() >= 64) {
                    throw new RuntimeException("More than 64 teams cannot be added.");
                }
                this.footballClubs.add(footballClub);
            }
        }
    }

    public void resetList(){
        footballClubs = new ArrayList<FootballClub>();
        groups = new ArrayList<Group>();
    }

    /**
     * Method for generating groups
     * Iterates through the clubs that have been added to the tournament and divides them into two list according to their nationality
     * It then randomizes the order to make the draw unpredictable
     * Then the groups are generated, with the requirements of 1 foreign and 3 norwegian teams
     * @return true when finished
     */
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
        }
        //randomizes both lists
        Collections.shuffle(norwegianClubs);
        Collections.shuffle(internationalClubs);
        for(int i = 0; i < 16; i++){
            this.groups.add(new Group());
            this.groups.get(i).setGroupNumber(i);
            this.groups.get(i).addTeam(internationalClubs.get(i));
            for (int k = 0; k < 3; k++){
                this.groups.get(i).addTeam(norwegianClubs.get(k +(3*i)));
            }
            this.groups.get(i).generateMatches();
        }
        return true;
    }



    @Override
    public String toString() {
        return "GroupController{" +
                "footballClubs=" + footballClubs +
                '}';
    }
}

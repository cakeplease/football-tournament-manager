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

    @Override
    public String toString() {
        return "GroupController{" +
                "footballClubs=" + footballClubs +
                '}';
    }
}

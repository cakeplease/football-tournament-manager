package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * GroupController class that stores football clubs and generates groups.
 */
public class GroupController {
    private ArrayList<FootballClub> footballClubs;

    /**
     * GroupController constructor
     */
    public GroupController() {
        this.footballClubs = new ArrayList<>(64);
    }

    public ArrayList<FootballClub> getFootballClubs() {
        return footballClubs;
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
            for(int i = 0; i < internationalClubs.size(); i++){
                for(int j = 0; j < norwegianClubs.size(); j++){
                    //TODO: write code here
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

package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "GroupController{" +
                "footballClubs=" + footballClubs +
                '}';
    }
}

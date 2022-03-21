package base;

import controller.GroupController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TournamentManager {
    //- contains all matches?
    //-
    private final GroupController groupController = new GroupController();
    private ArrayList<Match> roundOf16 = new ArrayList<>();
    private ArrayList<Match> quarterFinals = new ArrayList<>();

    public TournamentManager(){

    }

    public ArrayList<Match> getRoundOf16() {
        return roundOf16;
    }

    public ArrayList<Match> listGroupMatches(){
        ArrayList<Match> groupMatches = new ArrayList<>();
        for (int i = 0; i < groupController.getGroups().size(); i++){
            for (int j = 0; j < 6; j++)
            groupMatches.add(groupController.getGroups().get(i).getGroupMatches().get(j));
        }
        if (groupMatches.isEmpty()){
            return null;
        }else {
            return groupMatches;
        }
    }

    public boolean generateAfinalsRoundOf16(){
        ArrayList<FootballClub> groupWinners = new ArrayList<>();
        ArrayList<FootballClub> secondPlace = new ArrayList<>();
        for (int i = 0; i < groupController.getGroups().size(); i++){
            groupWinners.add(groupController.getGroups().get(i).getGroupTeams().get(0));
        }
        for (int j = 0; j < groupController.getGroups().size(); j++){
            secondPlace.add(groupController.getGroups().get(j).getGroupTeams().get(1));
        }
        Collections.shuffle(groupWinners);
        Collections.shuffle(secondPlace);
        for (int i = 0; i < groupWinners.size(); i++){
            this.roundOf16.add(new Match(groupWinners.get(i), secondPlace.get(i)));
        }
        if (this.roundOf16.isEmpty()){
            return false;
        }else {
            return true;
        }

    }

}

package base;

import controller.GroupController;

import java.util.ArrayList;

public class TournamentManager {
    //- contains all matches?
    //-
    private final GroupController groupController = new GroupController();

    public TournamentManager(){

    }

    public ArrayList<Match> listGroupMatches(){
        ArrayList<Match> groupMatches = new ArrayList<>();
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 6; j++)
            groupMatches.add(groupController.getGroups().get(i).getGroupMatches().get(j));
        }
        if (groupMatches.isEmpty()){
            return null;
        }else {
            return groupMatches;
        }
    }

    public void generateAfinalsRoundOf16(){
        ArrayList<FootballClub> groupWinners = new ArrayList<>();
    }

}
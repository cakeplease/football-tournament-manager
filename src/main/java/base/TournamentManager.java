package base;

import controller.GroupController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TournamentManager {
    //- contains all matches?
    //-
    private final GroupController groupController = new GroupController();
    private ArrayList<Match> roundOf16A = new ArrayList<>();
    private ArrayList<Match> roundOf16B = new ArrayList<>();
    private ArrayList<Match> quarterFinals = new ArrayList<>();

    public TournamentManager(){

    }

    public ArrayList<Match> getRoundOf16A() {
        return roundOf16A;
    }

    public ArrayList<Match> getRoundOf16B() {
        return roundOf16B;
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

    public boolean generateRoundOf16(){
        ArrayList<FootballClub> groupWinners = new ArrayList<>();
        ArrayList<FootballClub> secondPlace = new ArrayList<>();
        ArrayList<FootballClub> thirdPlace = new ArrayList<>();
        ArrayList<FootballClub> fourthPlace = new ArrayList<>();

        for (int i = 0; i < groupController.getGroups().size(); i++){
            groupWinners.add(groupController.getGroups().get(i).getGroupTeams().get(0));
        }
        for (int j = 0; j < groupController.getGroups().size(); j++){
            secondPlace.add(groupController.getGroups().get(j).getGroupTeams().get(1));
        }
        for (int k = 0; k < groupController.getGroups().size(); k++){
            thirdPlace.add(groupController.getGroups().get(k).getGroupTeams().get(2));
        }
        for (int l = 0; l < groupController.getGroups().size(); l++){
            fourthPlace.add(groupController.getGroups().get(l).getGroupTeams().get(3));
        }
        Collections.shuffle(groupWinners);
        Collections.shuffle(secondPlace);
        Collections.shuffle(thirdPlace);
        Collections.shuffle(fourthPlace);
        for (int i = 0; i < groupWinners.size(); i++){
            this.roundOf16A.add(new Match(groupWinners.get(i), secondPlace.get(i)));
        }
        for (int i = 0; i < thirdPlace.size(); i++){
            this.roundOf16B.add(new Match(thirdPlace.get(i), fourthPlace.get(i)));
        }
        return !roundOf16A.isEmpty() && !roundOf16B.isEmpty();
    }

}

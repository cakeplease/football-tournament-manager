package base;

import controller.GroupController;

import java.util.ArrayList;
import java.util.Collections;

public class TournamentManager {
    private final GroupController groupController = new GroupController();
    private final ArrayList<Match> roundOf32A = new ArrayList<>();
    private final ArrayList<Match> roundOf32B = new ArrayList<>();
    private final ArrayList<Match> roundOf16A = new ArrayList<>();
    private final ArrayList<Match> roundOf16B = new ArrayList<>();
    private final ArrayList<Match> quarterFinalsA = new ArrayList<>();
    private final ArrayList<Match> quarterFinalsB = new ArrayList<>();
    private final ArrayList<Match> semifinalsA = new ArrayList<>();
    private final ArrayList<Match> semifinalsB = new ArrayList<>();

    public TournamentManager(){

    }

    public ArrayList<Match> getRoundOf32A() {
        return roundOf32A;
    }

    public ArrayList<Match> getRoundOf32B() {
        return roundOf32B;
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

    public boolean generateRoundOf32(){
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
            this.roundOf32A.add(new Match(groupWinners.get(i), secondPlace.get(i)));
        }
        for (int i = 0; i < thirdPlace.size(); i++){
            this.roundOf32B.add(new Match(thirdPlace.get(i), fourthPlace.get(i)));
        }
        return !roundOf32A.isEmpty() && !roundOf32B.isEmpty();
    }

    public boolean generateRoundOf16(){
        ArrayList<FootballClub> winnersA = new ArrayList<>();
        ArrayList<FootballClub> winnersB = new ArrayList<>();
        for (int i = 0; i < 16; i++){
            winnersA.add(roundOf32A.get(i).getWinner());
        }
        for (int i = 0; i < 16; i++){
            winnersB.add(roundOf32B.get(i).getWinner());
        }
        Collections.shuffle(winnersA);
        Collections.shuffle(winnersB);
        for (int i = 0; i < 8; i++){
            this.roundOf16A.add(new Match(winnersA.get(i), winnersA.get(15-i)));
        }
        for (int i = 0; i < 8; i++){
            this.roundOf16B.add(new Match(winnersB.get(i), winnersB.get(15-i)));
        }
        return !roundOf16A.isEmpty() && !roundOf16B.isEmpty();
    }


    public boolean generateQuarterFinals(){
        ArrayList<FootballClub> winnersA = new ArrayList<>();
        ArrayList<FootballClub> winnersB = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            winnersA.add(roundOf16A.get(i).getWinner());
        }
        for (int i = 0; i < 8; i++){
            winnersB.add(roundOf16B.get(i).getWinner());
        }
        Collections.shuffle(winnersA);
        Collections.shuffle(winnersB);
        for (int i = 0; i < 4; i++){
            this.quarterFinalsA.add(new Match(winnersA.get(i), winnersA.get(7-i)));
        }
        for (int i = 0; i < 4; i++){
            this.quarterFinalsB.add(new Match(winnersB.get(i), winnersB.get(7-i)));
        }
        return !quarterFinalsA.isEmpty() && !quarterFinalsB.isEmpty();
    }

    public boolean generateSemifinals(){
        ArrayList<FootballClub> winnersA = new ArrayList<>();
        ArrayList<FootballClub> winnersB = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            winnersA.add(quarterFinalsA.get(i).getWinner());
        }
        for (int i = 0; i < 4; i++){
            winnersB.add(quarterFinalsB.get(i).getWinner());
        }
        Collections.shuffle(winnersA);
        Collections.shuffle(winnersB);
        for (int i = 0; i < 2; i++){
            this.semifinalsA.add(new Match(winnersA.get(i), winnersA.get(3-i)));
        }
        for (int i = 0; i < 2; i++){
            this.semifinalsB.add(new Match(winnersB.get(i), winnersB.get(3-i)));
        }
        return !quarterFinalsA.isEmpty() && !quarterFinalsB.isEmpty();
    }
}

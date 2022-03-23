package base;

import controller.GroupController;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the tournament manager, has methods to generate the knockout matches and list the group matches.
 *
 * @author danielnesvag
 * @version 22.03.2022
 */
public class TournamentManager {
    private static GroupController groupController;
    private final ArrayList<Match> roundOf32A = new ArrayList<>(); //a list of the round of 32 matches in the A finals
    private final ArrayList<Match> roundOf32B = new ArrayList<>(); //a list of the round of 32 matches in the B finals
    private final ArrayList<Match> roundOf16A = new ArrayList<>(); //a list of the round of 16 matches in the A finals
    private final ArrayList<Match> roundOf16B = new ArrayList<>(); //a list of the round of 16 matches in the B finals
    private final ArrayList<Match> quarterFinalsA = new ArrayList<>(); //a list of the quarterfinals matches in the A finals
    private final ArrayList<Match> quarterFinalsB = new ArrayList<>(); //a list of the quarterfinals matches in the B finals
    private final ArrayList<Match> semifinalsA = new ArrayList<>(); //a list of the semifinals matches in the A finals
    private final ArrayList<Match> semifinalsB = new ArrayList<>(); //a list of the semifinals matches in the B finals

    public TournamentManager (GroupController gc){
        groupController = gc;
    }

    /**
     * Returns the group controller
     *
     * @return groupController reference
     */
    public GroupController getGroupController(){
        return groupController;
    }

    /**
     * Returns the round of 32 in the A finals
     *
     * @return roundOf32A
     */
    public ArrayList<Match> getRoundOf32A() {
        return roundOf32A;
    }

    /**
     * Returns the round of 32 in the B finals
     *
     * @return roundOf32B
     */
    public ArrayList<Match> getRoundOf32B() {
        return roundOf32B;
    }

    /**
     * Returns the round of 16 in the A finals
     *
     * @return roundOf16A
     */
    public ArrayList<Match> getRoundOf16A() {
        return roundOf16A;
    }

    /**
     * Returns the round of 16 in the B finals
     *
     * @return roundOf16B
     */
    public ArrayList<Match> getRoundOf16B() {
        return roundOf16B;
    }

    /**
     * Returns the quarterfinals in the A finals
     *
     * @return quarterFinalsA
     */
    public ArrayList<Match> getQuarterFinalsA() {
        return quarterFinalsA;
    }

    /**
     * Returns the quarterfinals in the B finals
     *
     * @return quarterFinalsB
     */
    public ArrayList<Match> getQuarterFinalsB() {
        return quarterFinalsB;
    }

    /**
     * Returns the semifinals in the A finals
     *
     * @return semifinalsA
     */
    public ArrayList<Match> getSemifinalsA() {
        return semifinalsA;
    }

    /**
     * Returns the semifinals in the B finals
     *
     * @return semifinalsB
     */
    public ArrayList<Match> getSemifinalsB() {
        return semifinalsB;
    }

    /**
     * Lists the group matches in the tournament. The group matches is created in the Groupcontroller.
     * The method puts the matches in a list.
     * @return the list of group matches or null if the list is empty.
     */
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

    /**
     * Generates the round of 32 for both the A finals and B finals and adds them to a list of matches.
     * @return true if they were generated and false if they are empty.
     */
    public boolean generateRoundOf32(){
        //Creates four lists, which separates the winners, second-places, third-places and fourth-places
        ArrayList<FootballClub> groupWinners = new ArrayList<>();
        ArrayList<FootballClub> secondPlace = new ArrayList<>();
        ArrayList<FootballClub> thirdPlace = new ArrayList<>();
        ArrayList<FootballClub> fourthPlace = new ArrayList<>();

        /*adds the teams to the lists. The winners are at index 0 in the groups, therefore we
        collect these and adds them to the list of winners, and we do the same thing to the second-places etc.*/
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
        //Shuffles the lists which makes the matches random.
        Collections.shuffle(groupWinners);
        Collections.shuffle(secondPlace);
        Collections.shuffle(thirdPlace);
        Collections.shuffle(fourthPlace);
        /*Creates the matches and adds them to the round of 32 list. The winner face a team which came
         in second place in another group and the third-places face a team which came in fourth place*/
        for (int i = 0; i < groupWinners.size(); i++){
            this.roundOf32A.add(new Match(groupWinners.get(i), secondPlace.get(i)));
        }
        for (int i = 0; i < thirdPlace.size(); i++){
            this.roundOf32B.add(new Match(thirdPlace.get(i), fourthPlace.get(i)));
        }
        return !roundOf32A.isEmpty() && !roundOf32B.isEmpty();
    }

    /**
     * Generates the round of 16 for both A finals and B finals. Is very similar to the generateRoundOf32 method.
     * @return true if the lists contain matches and false if they are empty.
     */
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

    /**
     * Generates the quarterfinals for both A finals and B finals.
     * @return true if the lists contain matches and false if they are empty.
     */
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

    /**
     * Generates the semifinals for both the A finals and B finals.
     * @return true if the lists contain matches and false if they are empty.
     */
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

    /**
     * Generate the final for the A finals.
     *
     * @return the A final Match between two teams.
     */
    public Match generateFinalA(){
        ArrayList<FootballClub> finalistsA = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            finalistsA.add(semifinalsA.get(i).getWinner());
        }
        return new Match(finalistsA.get(0), finalistsA.get(1));
    }

    /**
     * Generate the final for the B finals
     *
     * @return the B final between two teams.
     */
    public Match generateFinalB(){
        ArrayList<FootballClub> BFinalists = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            BFinalists.add(semifinalsB.get(i).getWinner());
        }
        return new Match(BFinalists.get(0), BFinalists.get(1));
    }
}

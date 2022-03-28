package base;

import model.DataHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class for controlling group play
 *
 * @author birkn
 * @version 1.03 23.03.2022
 */
public class Group {
    private final ArrayList<FootballClub> groupTeams;
    private ArrayList<Match> groupMatches = null;
    private final Random rand = new Random();
    private boolean hasEnded = false;
    private int groupNumber;





    /**
     * Constructor for getting predetermined group
     * Assigns groupTeams to existing arraylist
     * @param groupTeams existing arraylist of type FootballClub
     * @throws IllegalArgumentException if arraylist size larger then 4 or if array is null
     */
    public Group(ArrayList<FootballClub> groupTeams) throws IllegalArgumentException{
        if (groupTeams == null) {
            throw new IllegalArgumentException("group teams cant be null");
        } else
        if (groupTeams.size() > 4){
            throw new IllegalArgumentException("Group size larger then allowed");
        }
        this.groupTeams = groupTeams;
    }

    /**
     * Overload constructor if group is not determined beforehand
     */
    public Group(){
        this.groupTeams = new ArrayList<FootballClub>();
    }

    public ArrayList<FootballClub> getGroupTeams() {
        return groupTeams;
    }

    public ArrayList<Match> getGroupMatches() {
        return groupMatches;
    }

    /**
     * Adds team to group
     * @param teamToAdd team to add to group
     * @return boolean if team is added or not
     * @throws RuntimeException if arraysize >= 4
     */
    public boolean addTeam(FootballClub teamToAdd) throws RuntimeException{
        if (groupTeams.size() >= 4)
            throw new RuntimeException("Groups cant have more then 4 teams");
        if(this.groupTeams.contains(teamToAdd))
            return false;

        this.groupTeams.add(teamToAdd);
        return true;
    }


    /**
     * get index of grupe
     * @return grupeNumber
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * set index of grupe
     * @param groupNumber grupe number
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * set if grupe can change after load in case we no longe are in grupe stage
     * @param hasEnded
     */
    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    /**
     * gives the csv format for the grupe
     * @return csv string
     */
    public String generateCsv(){
        return groupNumber + ";" + groupTeams.stream()
                .map(FootballClub::getCsvFormat)
                .collect(Collectors.joining(";")) + ";" + hasEnded + "\n";
    }


    /**
     * Generates the matches
     * Then adds to matches-array
     * Sets the starting score of a match to 0-0
     * @throws RuntimeException if group size != 4
     */
    public void generateMatches() throws RuntimeException {
        if (this.groupTeams.size() != 4){
            throw new RuntimeException("There needs to be 4 teams in the group");
        }else if(this.groupMatches == null){
            this.groupMatches = new ArrayList<Match>();

            //loops threw the array and adds all matches to match array
            for (int i = 0; i < this.groupTeams.size(); i++) {
                for (int j = i + 1; j < this.groupTeams.size(); j++) {
                    this.groupMatches.add(new Match(this.groupTeams.get(i), this.groupTeams.get(j), 0, 0));
                }
            }
        }
    }

    /**
     * adds match to matches array
     * creates array if array dose not exist
     * used when loading matches
     * @param m match to add
     */
    public void addMatchOnLoad(Match m){
        if(this.groupMatches == null)
            this.groupMatches = new ArrayList<Match>();

        this.groupMatches.add(m);

    }

    /**
     * sorts the arraylist, and adds score and gols to footballclubs
     */
    public void endGroup(){


        //force the endGroup to only run once
        if (hasEnded)
            return;

        hasEnded = true;

        //run threw all matches and set the football clubs score

        groupMatches.forEach(e->{
            FootballClub w = e.getWinner();
            if (w == null){
                e.getFootballClub1().setGroupScore(e.getFootballClub1().getGroupScore() + 1);
                e.getFootballClub2().setGroupScore(e.getFootballClub2().getGroupScore() + 1);
            }else
                e.getWinner().setGroupScore(e.getWinner().getGroupScore() + 3);

            e.getFootballClub1().setGoalsScored(e.getFootballClub1().getGoalsScored() + e.getScore1());
            e.getFootballClub2().setGoalsScored(e.getFootballClub2().getGoalsScored() + e.getScore2());

            e.getFootballClub1().setGoalsLetIn(e.getFootballClub1().getGoalsLetIn() + e.getScore2());
            e.getFootballClub2().setGoalsLetIn(e.getFootballClub2().getGoalsLetIn() + e.getScore1());
        });

        //sorts the teams after score and goal diff
        groupTeams.sort((o1, o2) -> {
            int sortScore = o1.getGroupScore() - o2.getGroupScore();
            if (sortScore == 0 & o1.getGoalsScored() - o1.getGoalsLetIn() != o2.getGoalsScored() - o2.getGoalsLetIn())
                sortScore = (o1.getGoalsScored() - o1.getGoalsLetIn() > o2.getGoalsScored() - o2.getGoalsLetIn()) ? 1 : -1;
            else if(sortScore == 0)
                sortScore = (rand.nextInt() >= 0.5) ? 1 : -1;
            return sortScore;
        });
    }

    /**
     * for testing
     * simulates all matches in group
     */
    public void testSimulateAllMatches(){
        if (groupMatches != null){
            groupMatches.forEach(e->{
                e.setScore1((int)(rand.nextDouble()*4));
                e.setScore2((int)(rand.nextDouble()*4));
            });
        }
    }
}

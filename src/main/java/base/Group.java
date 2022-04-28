package base;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class for controlling group play
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
    public Group(ArrayList<FootballClub> groupTeams) throws IllegalArgumentException {
        if (groupTeams == null) {
            throw new IllegalArgumentException("group teams cant be null");
        } else
        if (groupTeams.size() > 4) {
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

    /**
     * Get all football clubs from the group
     * @return group teams
     */
    public ArrayList<FootballClub> getGroupTeams() {
        return groupTeams;
    }

    /**
     * Get all matches played between the football clubs of the group
     * @return group matches
     */
    public ArrayList<Match> getGroupMatches() {
        return groupMatches;
    }

    /**
     * Adds team to group
     * @param teamToAdd team to add to group
     * @return boolean if team is added or not
     * @throws RuntimeException if the team count is less than 4
     */
    public boolean addTeam(FootballClub teamToAdd) throws RuntimeException {
        if (groupTeams.size() >= 4) {
            throw new RuntimeException("Groups cannot have more then 4 teams");
        }
        if (this.groupTeams.contains(teamToAdd)) {
            return false;
        }

        this.groupTeams.add(teamToAdd);
        return true;
    }

    /**
     * Get index of the group
     * @return group number
     */
    public int getGroupNumber() {
        return groupNumber;
    }

    /**
     * Set index of group
     * @param groupNumber group number
     */
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    /**
     * Set if group can change after load in case we no longer are in group stage
     * @param hasEnded boolean
     */
    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    /**
     * Gives the csv format for the group
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
     * @throws RuntimeException if group size is not 4
     */
    public void generateMatches() throws RuntimeException {
        if (this.groupTeams.size() != 4){
            throw new RuntimeException("There need to be 4 teams in the group");
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
     * Adds match to matches array
     * Creates array if array does not exist
     * used when loading matches
     * @param m match to add
     */
    public void addMatchOnLoad(Match m) {
        if(this.groupMatches == null) {
            this.groupMatches = new ArrayList<Match>();
        }

        this.groupMatches.add(m);
    }

    /**
     * Sorts the arraylist, and adds score and goals to football clubs
     */
    public void endGroup(){
        //force the endGroup to only run once
        if (hasEnded) {
            return;
        }

        hasEnded = true;

        //run threw all matches and set the football clubs score

        groupMatches.forEach(e-> {
            FootballClub w = e.getWinner();
            if (w == null) {
                e.getFootballClub1().setGroupScore(e.getFootballClub1().getGroupScore() + 1);
                e.getFootballClub2().setGroupScore(e.getFootballClub2().getGroupScore() + 1);
            } else {
                e.getWinner().setGroupScore(e.getWinner().getGroupScore() + 3);
            }

            e.getFootballClub1().setGoalsScored(e.getFootballClub1().getGoalsScored() + e.getScore1());
            e.getFootballClub2().setGoalsScored(e.getFootballClub2().getGoalsScored() + e.getScore2());

            e.getFootballClub1().setGoalsLetIn(e.getFootballClub1().getGoalsLetIn() + e.getScore2());
            e.getFootballClub2().setGoalsLetIn(e.getFootballClub2().getGoalsLetIn() + e.getScore1());
        });

        //sorts the teams after score and goal diff
        groupTeams.sort((o1, o2) -> {
            int sortScore = o1.getGroupScore() - o2.getGroupScore();
            if (sortScore == 0 & o1.getGoalsScored() - o1.getGoalsLetIn() != o2.getGoalsScored() - o2.getGoalsLetIn()) {
                sortScore = (o1.getGoalsScored() - o1.getGoalsLetIn() > o2.getGoalsScored() - o2.getGoalsLetIn()) ? 1 : -1;

            } else if(sortScore == 0) {
                sortScore = (rand.nextInt() >= 0.5) ? 1 : -1;
            }

            return sortScore;
        });
    }

    /**
     * For testing purposes
     * Simulates all matches in group
     */
    public void testSimulateAllMatches() {
        if (groupMatches != null) {
            groupMatches.forEach(e-> {
                if(e.getScore2() > 0 | e.getScore1() > 0)
                    return;
                e.setScore1((int)(rand.nextDouble()*4));
                e.setScore2((int)(rand.nextDouble()*4));
                e.setTime((int)(Math.random()*5 + 9) + ":" + (int)(Math.random()*60));
                e.setDate("03.05.2022");
            });
        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupTeams=" + groupTeams +
                ", groupMatches=" + groupMatches +
                ", rand=" + rand +
                ", hasEnded=" + hasEnded +
                ", groupNumber=" + groupNumber +
                '}';
    }
}

package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.ArrayList;

/**
 * Class for controlling group play
 *
 * @author birkn
 * @version 1.01 15.03.2022
 */
public class Group {
    private final ArrayList<FootballClub> groupTeams;
    private ArrayList<Match> groupMatches = null;

    /**
     * constructor for getting predetermined group
     * assigns groupTeams to existing arraylist
     * @param groupTeams existing arraylist of type fotbalclub
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
     * overload constructer if group is not determined beforhand
     */
    public Group(){
        this.groupTeams = new ArrayList<FootballClub>();
    }

    /**
     * adds team to group
     * @param teamToAdd team to add to group
     * @return boolean if team is added or not
     * @throws IndexOutOfBoundsException if arraysize >= 4
     */
    public boolean addTeam(FootballClub teamToAdd) throws IndexOutOfBoundsException{
        if (groupTeams.size() >= 4)
            throw new IndexOutOfBoundsException("Groups cant have more then 4 teams");
        if(this.groupTeams.contains(teamToAdd))
            return false;

        this.groupTeams.add(teamToAdd);
        return true;
    }

    /**
     * generates the matches
     * and adds to matches array
     * @throws Exception if group size != 4
     */
    public void genereateMatches() throws Exception {
        if (this.groupTeams.size() != 4){
            throw new Exception("There needs to be 4 teams in the group");
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
}

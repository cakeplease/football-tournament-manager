package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.ArrayList;

/**
 * Class for controlling groupe play
 *
 * @author birkn
 * @version 1.01 15.03.2022
 */
public class Group {
    private final ArrayList<FootballClub> groupeTeams;
    private ArrayList<Match> groupeMatches = null;

    /**
     * constructor for getting predetermined groupe
     * assigns groupeTeams to existing arraylist
     * @param groupeTeams existing arraylist of type fotbalclub
     * @throws IllegalArgumentException if arraylist size larger then 4 or if array is null
     */
    public Group(ArrayList<FootballClub> groupeTeams) throws IllegalArgumentException{
        if (groupeTeams == null) {
            throw new IllegalArgumentException("grope teams cant be null");
        } else
        if (groupeTeams.size() > 4){
            throw new IllegalArgumentException("Groupe size larger then allowed");
        }
        this.groupeTeams = groupeTeams;
    }

    /**
     * overload constructer if groupe is not determined beforhand
     */
    public Group(){
        this.groupeTeams = new ArrayList<FootballClub>();
    }

    /**
     * adds team to groupe
     * @param teamToAdd team to add to groupe
     * @return boolean if team is added or not
     * @throws IndexOutOfBoundsException if arraysize >= 4
     */
    public boolean addTeam(FootballClub teamToAdd) throws IndexOutOfBoundsException{
        if (groupeTeams.size() >= 4)
            throw new IndexOutOfBoundsException("Groupes cant have more then 4 teams");
        if(this.groupeTeams.contains(teamToAdd))
            return false;

        this.groupeTeams.add(teamToAdd);
        return true;
    }

    /**
     * generates the matches
     * and adds to matches array
     * @throws Exception if group size != 4
     */
    public void genereateMatches() throws Exception {
        if (this.groupeTeams.size() != 4){
            throw new Exception("There needs to be 4 teams in the groupe");
        }else if(this.groupeMatches == null){
            this.groupeMatches = new ArrayList<Match>();

            //loops threw the array and adds all matches to match array
            for (int i = 0; i < this.groupeTeams.size(); i++) {
                for (int j = i + 1; j < this.groupeTeams.size(); j++) {
                    this.groupeMatches.add(new Match(this.groupeTeams.get(i), this.groupeTeams.get(j), 0, 0));
                }
            }
        }
    }
}

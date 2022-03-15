package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.ArrayList;

/**
 * Class for controlling grupe play
 *
 * @author birkn
 * @version 1.01 15.03.2022
 */
public class Group {
    private final ArrayList<FootballClub> grupeTeams;
    private ArrayList<Match> grupeMatches = null;

    /**
     * constructor for getting predetermined grupe
     * assignes grupeTeams to existing arraylist
     * @param grupeTeams existing arraylist of type fotbalclub
     * @throws IllegalArgumentException if arraylist size larger then 4 or if array is null
     */
    public Group(ArrayList<FootballClub> grupeTeams) throws IllegalArgumentException{
        if (grupeTeams == null) {
            throw new IllegalArgumentException("grope teams cant be null");
        } else
        if (grupeTeams.size() > 4){
            throw new IllegalArgumentException("Grupe size larger then allowed");
        }
        this.grupeTeams = grupeTeams;
    }

    /**
     * overload constructer if grupe is not determined beforhand
     */
    public Group(){
        this.grupeTeams = new ArrayList<FootballClub>();
    }

    /**
     * adds team to grupe
     * @param teamToAdd team to add to grupe
     * @return boolean if team is added or not
     * @throws IndexOutOfBoundsException if arraysize >= 4
     */
    public boolean addTeam(FootballClub teamToAdd) throws IndexOutOfBoundsException{
        if (grupeTeams.size() >= 4)
            throw new IndexOutOfBoundsException("Grupes cnat have more then 4 teams");
        if(this.grupeTeams.contains(teamToAdd))
            return false;

        this.grupeTeams.add(teamToAdd);
        return true;
    }

    /**
     * generates the matches
     * and adds to matches array
     * @throws Exception if group size != 4
     */
    public void genereateMatches() throws Exception {
        if (this.grupeTeams.size() != 4){
            throw new Exception("There needs to be 4 teams in the grupe");
        }else if(this.grupeMatches == null){
            this.grupeMatches = new ArrayList<Match>();

            //loops threw the array and adds all matches to match array
            for (int i = 0; i < this.grupeTeams.size(); i++) {
                for (int j = i + 1; j < this.grupeTeams.size(); j++) {
                    this.grupeMatches.add(new Match(this.grupeTeams.get(i), this.grupeTeams.get(j), 0, 0));
                }
            }
        }
    }
}

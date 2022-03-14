package no.ntnu.idatt1002.k0208.footballtournamentapplication;

public abstract class Match {
    private FootballClub footballClub1;
    private FootballClub footballClub2;
    private int score;

    /**
     * Creates an instance of a footballmatch between to teams
     * @param footballClub1 the first football club
     * @param footballClub2 the second football club
     * @throws NullPointerException if one of the clubs is null
     */
    public Match(FootballClub footballClub1, FootballClub footballClub2)throws NullPointerException{
        if (footballClub1 == null || footballClub2 == null){
            throw new NullPointerException("The football club can not be null!!");
        }
        this.footballClub1 = footballClub1;
        this.footballClub2 = footballClub2;
    }

    public FootballClub getFootballClub1() {
        return footballClub1;
    }

    public FootballClub getFootballClub2() {
        return footballClub2;
    }

    public int getScore() {
        return score;
    }


}

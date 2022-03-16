package no.ntnu.idatt1002.k0208.footballtournamentapplication;

public class Match {
    private final FootballClub footballClub1;
    private final FootballClub footballClub2;
    private final int score1;
    private final int score2;
    private FootballClub winner;

    /**
     * Creates an instance of a footballmatch between to teams
     * @param footballClub1 the first football club
     * @param footballClub2 the second football club
     * @throws IllegalArgumentException if one of the scores are negative
     * @throws NullPointerException if one of the football clubs are null
     */
    public Match(FootballClub footballClub1, FootballClub footballClub2, int score1, int score2)throws IllegalArgumentException, NullPointerException{
        if (footballClub1 == null || footballClub2 == null){
            throw new NullPointerException("The football club can not be null!!");
        }
        if (score1 < 0 || score2 < 0){
            throw new IllegalArgumentException("The score can not be negative!!");
        }
        this.footballClub1 = footballClub1;
        this.footballClub2 = footballClub2;
        this.score1 = score1;
        this.score2 = score2;
    }

    /**
     * Return the first football club
     *
     * @return footballClub1
     */
    public FootballClub getFootballClub1() {
        return footballClub1;
    }

    /**
     * Return the second football club
     *
     * @return footballClub2
     */
    public FootballClub getFootballClub2() {
        return footballClub2;
    }

    /**
     * Return the first score, which belongs to footballClub1
     *
     * @return score1
     */
    public int getScore1() {
        return score1;
    }

    /**
     * Return the second score which belongs to footballClub2
     *
     * @return score2
     */
    public int getScore2() {
        return score2;
    }

    /**
     * Sets the football club to winner
     * @param winner the football club which won the match
     */
    public void setWinner(FootballClub winner) {
        this.winner = winner;
    }

    /**
     * Method which returns the victorious football club from the match.
     * If the match results in a tie it returns null.
     *
     * @return the winner or null.
     */
    public FootballClub getWinner(){
        if (score1 < score2){
            setWinner(footballClub2);
            return winner;
        } else if(score1 > score2){
            setWinner(footballClub1);
            return winner;
        }else{
            return null;
        }
    }
}

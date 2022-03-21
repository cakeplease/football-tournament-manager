package base;

/**
 * Creates an instance of a match between two football clubs.
 *
 * @author danielnesvag
 * @version 16.03.2022
 */
public class Match {
    private final FootballClub footballClub1; //the first football club in the match
    private final FootballClub footballClub2; //the second football club in the match
    private int score1 = 0; //the score of the first team
    private int score2 = 0; //the score of the second team
    private FootballClub winner = null; //the winner of the match
    private int time; //the time of when the match is played, in format (hhmm)
    private int date; //the date of when the match is played, in format (ddmmyyyy)
    private int fieldNr; //the field where the match is played

    /**
     * Creates an instance of a football match.
     * @param footballClub1 the first football club
     * @param footballClub2 the second football club
     * @throws NullPointerException if one of the clubs are null
     */
    public Match(FootballClub footballClub1, FootballClub footballClub2)throws NullPointerException{
        if (footballClub1 == null || footballClub2 == null){
            throw new NullPointerException("The football club can not be null!!");
        }
        this.footballClub1 = footballClub1;
        this.footballClub2 = footballClub2;
    }

    /**
     * Creates an instance of a football match between to teams
     * @param footballClub1 the first football club
     * @param footballClub2 the second football club
     * @param score1 the score of the first team
     * @param score2 the score of the second team
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
     * Sets the first score
     *
     * @param score1 the first score
     */
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    /**
     * Sets the second score
     *
     * @param score2 the second score
     */
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    /**
     * Sets the time of the match
     *
     * @param time when the match is played
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Sets the date of the match
     *
     * @param date what date the match is played
     */
    public void setDate(int date) {
        this.date = date;
    }

    /**
     * Sets the field of where the match is played
     *
     * @param fieldNr which field it is played
     */
    public void setFieldNr(int fieldNr) {
        this.fieldNr = fieldNr;
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
        if (this.winner != null) return this.winner;

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

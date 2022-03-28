package base;

import model.DataHandler;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * FootballClub class
 */
public class FootballClub {
    private String name;
    private String nationality;
    private int goalsScored;
    private int goalsLetIn;
    private int groupScore;

    /**
     * Constructor for FootballClub
     * @param name the name of the club
     * @param nationality which country the club is from
     */
    public FootballClub(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    /**
     * overload constructor for creation of Footballclub objects from file in DataStorage class
     * @see model.DataStorage
     * @param name
     * @param nationality
     * @param goalsScored
     * @param goalsLetIn
     * @param groupScore
     */
    public FootballClub(String name, String nationality, int goalsScored, int goalsLetIn, int groupScore){
        this.name = name;
        this.nationality = nationality;
        this.goalsScored = goalsScored;
        this.goalsLetIn = goalsLetIn;
        this.groupScore = groupScore;
    }

    /**
     * Method to get the name of the club
     * @return the name of the club
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the nationality of the club
     * @return the nationality of the club
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Method to get the amount of goals a club has scored
     * @return the amount of goals a club has scored
     */
    public int getGoalsScored() {
        return goalsScored;
    }

    /**
     * Method to get the amount of goals a club has conceded
     * @return the amount of goals a club has conceded
     */
    public int getGoalsLetIn() {
        return goalsLetIn;
    }

    /**
     * Method to set the amount of goals a club has scored
     * @param goalsScored the total amount of goals the club has scored
     */
    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    /**
     * Method to set the amount of goals a club has conceded
     * @param goalsLetIn the total amount of goals the club has conceded
     */
    public void setGoalsLetIn(int goalsLetIn) {
        this.goalsLetIn = goalsLetIn;
    }

    /**
     * setter for groupe score
     * @param score
     */
    public void setGroupScore(int score){
        this.groupScore = score;
    }

    /**
     * getter for grupe score
     * @return group score
     */
    public int getGroupScore() {
        return this.groupScore;
    }

    /**
     * Method to save the information of the football clubs to a csv-file TODO: check placement (in groupcontroller)
     */
    public void saveFootballClub() {
        String data = this.name + "," + this.nationality + "," + this.goalsScored + "," + this.goalsLetIn + "\n";
        Path path = Paths.get("src/main/resources/data/"+this.name+".csv");
        DataHandler.saveToFile(data, path);
    }


    public String getCsvFormat() {
        return this.name + "," + this.nationality + "," + this.goalsScored + "," + this.goalsLetIn +  "," + this.groupScore + "\n";
    }

    /**
     * Equals method to compare two football clubs
     * @param o an object of football club
     * @return true if equal/false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return goalsScored == that.goalsScored && goalsLetIn == that.goalsLetIn && name.equalsIgnoreCase(that.getName()) && nationality.equalsIgnoreCase(that.nationality);
    }

    /**
     * Method to assign a hashcode to an object of football club
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, goalsScored, goalsLetIn);
    }

    /**
     * ToString method for the FootballClub class
     * @return the values of the club in a String
     */
    @Override
    public String toString() {
        return "FootballClub{" +
                "name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", goalsScored=" + goalsScored +
                ", goalsLetIn=" + goalsLetIn +
                '}';
    }
}

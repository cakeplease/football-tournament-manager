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

    public FootballClub(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsLetIn() {
        return goalsLetIn;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public void setGoalsLetIn(int goalsLetIn) {
        this.goalsLetIn = goalsLetIn;
    }

    /*
    saveFootballClub original placement


    public void saveFootballClubs() {
        String data = this.name + "," + this.nationality + "," + this.goalsScored + "," + this.goalsLetIn + "\n";
        Path path = Paths.get("src/main/resources/data/"+this.name+".csv");
        DataHandler.saveToFile(data, path);
    }
    */

    public String getCsvFormat() {
        return this.name + "," + this.nationality + "," + this.goalsScored + "," + this.goalsLetIn + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballClub that = (FootballClub) o;
        return goalsScored == that.goalsScored && goalsLetIn == that.goalsLetIn && Objects.equals(name, that.name) && Objects.equals(nationality, that.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nationality, goalsScored, goalsLetIn);
    }

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

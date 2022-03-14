package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import java.util.Objects;

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

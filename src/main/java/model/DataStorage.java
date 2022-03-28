package model;

import base.FootballClub;
import controller.GroupController;
import javafx.beans.DefaultProperty;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class DataStorage {
    private static final String footballClubsPath = "src/main/resources/data/FootballClubs.csv";
    private static final String footballClubsTestPath = "src/main/resources/data/FootballClubsTest.csv";
    private static final String COMMA_DELIMITER = ",";


    /**
     * Method for saving football clubs to csv-file
     * Loops through the football clubs and adds the data to the cvs-file (path)
     */
    public static void saveFootballClubs(boolean test) {
        StringBuilder data = new StringBuilder();
        for (FootballClub fc : GroupController.getInstance().getFootballClubs()) {
            data.append(fc.getCsvFormat());
        }
        DataHandler.saveToFile(data.toString(), getFootballClubsPath(test));
    }

    /**
     * reads footballclubs from file, and creates array of them
     * @throws RuntimeException if footballclubs coud not be read from file
     */
    public static void loadFootballClubsFromFile(boolean test)throws RuntimeException{
        try {
            ArrayList<FootballClub> footballClubsFromFIle = new ArrayList<FootballClub>();
            Objects.requireNonNull(DataHandler.readFromFile(getFootballClubsPath(test))).forEach(e -> {
                String[] values = e.split(COMMA_DELIMITER);
                footballClubsFromFIle.add(new FootballClub(values[0],values[1], Integer.parseInt(values[2]),
                        Integer.parseInt(values[3]), Integer.parseInt(values[4])));
            });

            GroupController.getInstance().addAll(footballClubsFromFIle);
        } catch (NullPointerException e){
            throw new RuntimeException("No data found");
        }
    }

    /**
     * returns the path for file reading later or for tests
     * as path
     * @return footballclubs path
     */
    public static Path getFootballClubsPath(boolean test){
        return Paths.get((test) ? footballClubsTestPath : footballClubsPath);
    }


}

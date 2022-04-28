package model;

import base.FootballClub;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to read football clubs to an array
 */
public class FootballClubsFromFile {
    private static final Path path = Paths.get("footballclubs.csv");
    private static final String COMMA_DELIMITER = ";";

    /**
     * Read from csv file and return a football club array
     * @return footballclub array
     */
    public static ArrayList<FootballClub> readFromFile() {
        ArrayList<FootballClub> resultClubs = new ArrayList<FootballClub>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                resultClubs.add(new FootballClub(values[0], values[1]));
            }
            return resultClubs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package model;

import base.FootballClub;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * class to read football clubs to an array
 *
 * @author birkn
 * @version 1.01 23.03.2022
 */
public class FootballClubsFromFile {
    private static final Path path = Path.of("src\\main\\resources\\footballclubs.csv");
    private static final String COMMA_DELIMITER = ";";

    /**
     * read from csv file and return a footballclub array
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

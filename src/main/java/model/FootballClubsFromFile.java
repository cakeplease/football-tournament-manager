package model;

import base.FootballClub;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class fotballclubsFromFile {
    private static final Path path = Path.of("./resources/fotballclubs.csv")
    private static final String COMMA_DELIMITER = ",";

    /**
     * read from csv file and return a footballclub array
     * @return footballclub array
     */
    public static FootballClub[] readFromFile() {
        ArrayList<FootballClub> resultClubs = new ArrayList<FootballClub>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                resultClubs.add(new FootballClub(values[0], values[1]));
            }
            return resultClubs.toArray(FootballClub[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

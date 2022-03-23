package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
/**
 * Model part of the system.
 * Takes care of saving and reading from files
 */

public class DataHandler {

    /**
     * Method to save to file
     *
     * @param data String of data to save
     * @param path path, where to save it
     */
    public static void saveToFile(String data, Path path) {
        try {
            Files.writeString(path, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read from a file
     *
     * @param path path, where to read from
     */
    public static void readFromFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
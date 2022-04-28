package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

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
    public static void saveToFile(String data, Path path) throws RuntimeException{
        File file = new File(String.valueOf(path));
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                throw new RuntimeException("File could not be created");
            }
        }
        try {
            Files.writeString(path, data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read from a file
     *
     * @param path, where to read from
     */
    public static ArrayList<String> readFromFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(DataHandler.class.getClassLoader().getResourceAsStream(path), StandardCharsets.UTF_8))) {
            String line;
            ArrayList<String> data = new ArrayList<String>();
            while ((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }
            return data;
        } catch (IOException | NullPointerException e) {
            return null;
        }
    }
}
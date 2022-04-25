package model;

import base.FootballClub;
import base.Group;
import base.Match;
import base.TournamentManager;
import controller.GroupController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * class to save and load to and from file
 * @author birkn, vebjørn
 * @version 25.04.2022
 */
public class DataStorage {
    private static final String footballClubsPath = "src/main/resources/data/football-clubs.csv";
    private static final String footballClubsTestPath = "src/main/resources/test_data/football-clubs.csv";

    private static final String groupPath = "src/main/resources/data/groups-data.csv";
    private static final String groupPathTest = "src/main/resources/test_data/groups-data.csv";

    private static final String groupMatchesPath = "src/main/resources/data/groups-matches.csv";
    private static final String groupMatchesPathTest = "src/main/resources/test_data/groups-matches.csv";

    private static final String tournamentFinalsPath = "src/main/resources/data/tournament-finales.csv";
    private static final String tournamentFinalsPathTest = "src/main/resources/test_data/tournament-finales.csv";

    private static final String COMMA_DELIMITER = ",";


    /**
     * Method for saving football clubs to csv-file
     * Loops through the football clubs and adds the data to the cvs-file (path)
     * @throws RuntimeException if saveToFile fails
     */
    private static void saveFootballClubs() throws RuntimeException{
        StringBuilder data = new StringBuilder();
        for (FootballClub fc : GroupController.getInstance().getFootballClubs()) {
            data.append(fc.getCsvFormat()).append("\n");
        }
        DataHandler.saveToFile(data.toString(), Paths.get(footballClubsPath));
    }

    /**
     * Reads football clubs from file, and creates array of them
     * @throws RuntimeException when footballclubs can't be read from file
     */
    private static void loadFootballClubsFromFile(boolean isTestData) throws RuntimeException {
        Path path = Paths.get(footballClubsPath);
        if (isTestData) {
            path = Paths.get(footballClubsTestPath);
        }

        try {
            ArrayList<FootballClub> footballClubsFromFile = new ArrayList<FootballClub>();
            Objects.requireNonNull(DataHandler.readFromFile(path)).forEach(e ->
                    footballClubsFromFile.add(parseFootballClub(e)));
            GroupController.getInstance().addAll(footballClubsFromFile);
        } catch (NullPointerException e){
            throw new RuntimeException("No data found");
        }
    }

    /**
     * Save groups to file
     * @throws RuntimeException if saveToFile method fails
     */
    private static void saveGroupsToFile() throws RuntimeException{
        DataHandler.saveToFile(GroupController.getInstance().getGroups().stream()
                .map(Group::generateCsv)
                .collect(Collectors.joining("")), Paths.get(groupPath));
    }

    /**
     * Loads groups from file
     * @throws RuntimeException if groupSaveFile could not be read
     */
    private static void loadGroups(boolean isTestData) throws RuntimeException {
        Path path = (isTestData) ? Paths.get(groupPathTest) : Paths.get(groupPath);

        if(GroupController.getInstance().getGroups().size() > 0)
            GroupController.getInstance().getGroups().clear();
        try {
            DataHandler.readFromFile(path).forEach(e -> {
                String[] data = e.split(";");
                Group tempGroup = new Group();
                tempGroup.setGroupNumber(Integer.parseInt(data[0]));
                for (int i = 1; i < data.length - 1; i++) {
                    tempGroup.addTeam(getFootballClubReference(parseFootballClub(data[i])));
                }
                tempGroup.setHasEnded(Boolean.parseBoolean(data[data.length-1]));
                GroupController.getInstance().getGroups().add(tempGroup);
            });
        } catch (NullPointerException e) {
            throw new RuntimeException("Can't read data from file");
        }
    }

    /**
     * Creates football club from csv string
     * @param csvString to parse
     * @return Football club
     */
    private static FootballClub parseFootballClub(String csvString) {
        String[] values = csvString.split(COMMA_DELIMITER);
        return new FootballClub(values[0],values[1], Integer.parseInt(values[2]),
                Integer.parseInt(values[3]), Integer.parseInt(values[4]));
    }

    /**
     * Saves groupMatches to file
     * @throws RuntimeException if Datahandler.savetofile throws exception
     */
    private static void saveGroupMatches() throws RuntimeException{
        try {
            DataHandler.saveToFile(TournamentManager.getInstance().listGroupMatches()
                            .stream().map(Match::getCsv)
                            .collect(Collectors.joining("\n")),Paths.get(groupMatchesPath));
        } catch (NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Load matches from file and find group they exist inn then add the match to the group
     * @param isTestData if data to load is from test data path
     * @throws RuntimeException if file to read is not found or if grupes dose not exist
     */
    private static void loadGroupMatches(boolean isTestData) throws RuntimeException{
        Path path = (isTestData) ? Paths.get(groupMatchesPathTest) : Paths.get(groupMatchesPath);

        ArrayList<Group> groups = GroupController.getInstance().getGroups();

        if (groups.size() == 0)
            throw new RuntimeException("Cant add maches when grupes dont exist");

        try {
            DataHandler.readFromFile(path).forEach(e -> {
                String[] data = e.split(";");
                FootballClub f1 = parseFootballClub(data[0]);
                FootballClub f2 = parseFootballClub(data[1]);
                groups.forEach(x -> {
                    for (FootballClub y : x.getGroupTeams()) {
                        if (Objects.equals(y, f1)) {
                            x.addMatchOnLoad(new Match(getFootballClubReference(f1), getFootballClubReference(f2),
                                    Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                                    data[4], data[5], Integer.parseInt(data[6])));
                            break;
                        }
                    }
                });
            });
        }catch (NullPointerException e){
            throw new RuntimeException("Invalid file path");
        }
    }

    /**
     * Generates csv for all finals matches.
     * If no matches in bracket no matches will be written to file
     * Separates the matches with bracket names ("roundOf32A") as named in TournamentManger class
     * @throws RuntimeException if saveToFIle method fails
     */
    private static void saveTournamentFinals() throws RuntimeException{

        DataHandler.saveToFile("", Paths.get(tournamentFinalsPath));
        TournamentManager tr = TournamentManager.getInstance();
        ArrayList<String> dataToSave = new ArrayList<String>();

        dataToSave.add("roundOf32A");
        dataToSave.add(tr.getRoundOf32A().stream().map(Match::getCsv).collect(Collectors.joining("\n")));
        dataToSave.add("roundOf32B");
        dataToSave.add(tr.getRoundOf32B().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        dataToSave.add("roundOf16A");
        dataToSave.add(tr.getRoundOf16A().stream().map(Match::getCsv).collect(Collectors.joining("\n")));
        dataToSave.add("roundOf16B");
        dataToSave.add(tr.getRoundOf16B().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        dataToSave.add("quarterFinalsA");
        dataToSave.add(tr.getQuarterFinalsA().stream().map(Match::getCsv).collect(Collectors.joining("\n")));
        dataToSave.add("quarterFinalsB");
        dataToSave.add(tr.getQuarterFinalsB().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        dataToSave.add("semifinalsA");
        dataToSave.add(tr.getSemifinalsA().stream().map(Match::getCsv).collect(Collectors.joining("\n")));
        dataToSave.add("semifinalsB");
        dataToSave.add(tr.getSemifinalsB().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        dataToSave.add("FinalA");
        dataToSave.add(tr.getFinalA().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        dataToSave.add("FinalB");
        dataToSave.add(tr.getFinalB().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        //throws exception
        DataHandler.saveToFile(String.join("\n", dataToSave), Paths.get(tournamentFinalsPath));
    }

    /**
     * Loads all tournament finals matches to correct arrays
     * @throws RuntimeException if Datahandler.readfromfile fails or footballclub coud not be referenced
     */
    private static void loadFinalsMatches(boolean isTestData) throws RuntimeException{
        Path path = (isTestData) ? Paths.get(tournamentFinalsPathTest) : Paths.get(tournamentFinalsPath);
        TournamentManager tr = TournamentManager.getInstance();
        ArrayList<String> dataRead;

        //throws runtime exception
        dataRead = DataHandler.readFromFile(path);


        //reference to arraylist witch is currently adding too
        ArrayList<Match> currentWritingTo = null;

        //loops the read data and
        for(String e : dataRead){
            switch (e) {
                case "roundOf32A" -> currentWritingTo = tr.getRoundOf32A();
                case "roundOf32B" -> currentWritingTo = tr.getRoundOf32B();
                case "roundOf16A" -> currentWritingTo = tr.getRoundOf16A();
                case "roundOf16B" -> currentWritingTo = tr.getRoundOf16B();
                case "quarterFinalsA" -> currentWritingTo = tr.getQuarterFinalsA();
                case "quarterFinalsB" -> currentWritingTo = tr.getQuarterFinalsB();
                case "semifinalsA" -> currentWritingTo = tr.getSemifinalsA();
                case "semifinalsB" -> currentWritingTo = tr.getSemifinalsB();
                case "FinalA" -> currentWritingTo = tr.getFinalA();
                case "FinalB" -> currentWritingTo = tr.getFinalB();
                case "", "\n" -> {}
                default -> {
                    if(currentWritingTo == null)
                        break;
                    String[] data = e.split(";");
                    FootballClub f1 = parseFootballClub(data[0]);
                    FootballClub f2 = parseFootballClub(data[1]);

                    //adds to arraylist uses getFootballClubReference to get reference not instance
                    currentWritingTo.add(new Match(getFootballClubReference(f1), getFootballClubReference(f2),
                        Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        data[4], data[5], Integer.parseInt(data[6])));
                }
            }
        }

    }

    /**
     * Finds reference to footballclub in GroupController instead of copy
     * @param f footballclub to find
     * @return found club
     * @throws RuntimeException if reference was not found
     */
    private static FootballClub getFootballClubReference(FootballClub f) throws RuntimeException {
        ArrayList<FootballClub> footballClubs = GroupController.getInstance().getFootballClubs();
        int index = footballClubs.indexOf(f);
        if (index != -1) {
            return footballClubs.get(index);
        } else {
            throw new RuntimeException("Cannot find reference for club: " + f.toString());
        }
    }

    /**
     * saves all data
     */
    public static void save(){
        saveTournamentFinals();
        saveFootballClubs();
        saveGroupsToFile();
        saveGroupMatches();
    }

    /**
     * Loads all data
     */
      public static void load(){
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();

        loadFootballClubsFromFile(false);
        loadGroups(false);
        loadGroupMatches(false);
        loadFinalsMatches(false);
    }

    /**
     * loads all test data from "resources/test_data" folder
     * uses the is testdata parameter in the load methods
     */
    public static void loadTestData() {
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();

        loadFootballClubsFromFile(true);
        loadGroups(true);
        loadGroupMatches(true);
        loadFinalsMatches(true);
    }

}

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
 * @version 05.04.2022
 */
public class DataStorage {
    private static final String footballClubsPath = "src/main/resources/data/football-clubs.csv";
    private static final String footballClubsTestPath = "src/main/resources/data/test/football-clubs.csv";

    private static final String groupPath = "src/main/resources/data/groups-data.csv";
    private static final String groupPathTest = "src/main/resources/data/test/groups-data.csv";

    private static final String groupMatchesPath = "src/main/resources/data/groups-matches.csv";
    private static final String groupMatchesPathTest = "src/main/resources/data/test/groups-matches.csv";

    private static final String tournamentFinalsPath = "src/main/resources/data/tournament-finales.csv";
    private static final String tournamentFinalsPathTest = "src/main/resources/data/test/tournament-finales.csv";

    private static final String COMMA_DELIMITER = ",";


    /**
     * Method for saving football clubs to csv-file
     * Loops through the football clubs and adds the data to the cvs-file (path)
     */
    public static void saveFootballClubs() {
        StringBuilder data = new StringBuilder();
        for (FootballClub fc : GroupController.getInstance().getFootballClubs()) {
            data.append(fc.getCsvFormat() + "\n");
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
     */
    public static void saveGroupsToFile(){
        DataHandler.saveToFile(GroupController.getInstance().getGroups().stream().map(Group::generateCsv)
                .collect(Collectors.joining("")), Paths.get(groupPath));
    }

    /**
     * Loads groups from file
     * @throws RuntimeException if groupSaveFile could not be read
     */
    public static void loadGroups(boolean isTestData) throws RuntimeException {
        Path path = Paths.get(groupPath);
        if (isTestData) {
            path = Paths.get(groupPathTest);
        }
        try {
            Objects.requireNonNull(DataHandler.readFromFile(path)).forEach(e -> {
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
     */
    //TODO create file beforehand
    public static void saveGroupMatches(){
        try {
            System.out.println("save group matches runs");
            DataHandler.saveToFile(TournamentManager.getInstance().listGroupMatches()
                            .stream().map(Match::getCsv)
                            .collect(Collectors.joining("\n")),Paths.get(groupMatchesPath));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load matches from file and find group they exist inn then add the match to the group
     */
    public static void loadGroupMatches(boolean isTestData){
        Path path = Paths.get(groupMatchesPath);
        if (isTestData) {
            path = Paths.get(groupMatchesPathTest);
        }
        ArrayList<Group> groups = GroupController.getInstance().getGroups();

        DataHandler.readFromFile(path).forEach(e->{
            String[] data = e.split(";");
            FootballClub f1 = parseFootballClub(data[0]);
            FootballClub f2 = parseFootballClub(data[1]);
            groups.forEach(x-> {
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
    }

    /**
     * Generates csv for all finals matches.
     * If no matches in bracket no matches will be written to file
     * Separates the matches with bracket names ("roundOf32A") as named in TournamentManger class
     */
    public static void saveTournamentFinals(){

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

        dataToSave.add("FinalsMatches");
        dataToSave.add(tr.getFinalsMatches().stream().map(Match::getCsv).collect(Collectors.joining("\n")));

        DataHandler.saveToFile(String.join("\n", dataToSave), Paths.get(tournamentFinalsPath));
    }

    /**
     * Loads all tournament finals matches to correct arrays
     */
    public static boolean loadFinalsMatches(boolean isTestData){
        Path path = Paths.get(tournamentFinalsPath);
        if (isTestData) {
            path = Paths.get(tournamentFinalsPathTest);
        }
        TournamentManager tr = TournamentManager.getInstance();
        ArrayList<String> dataRead = DataHandler.readFromFile(path);

        if (dataRead == null)
            return false;

        ArrayList<Match> currentWritingTo = null;

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
                case "FinalsMatches" -> currentWritingTo = tr.getFinalsMatches();
                case "", "\n" -> {}
                default -> {
                    if(currentWritingTo == null)
                        break;
                    String[] data = e.split(";");
                    FootballClub f1 = parseFootballClub(data[0]);
                    FootballClub f2 = parseFootballClub(data[1]);

                    currentWritingTo.add(new Match(getFootballClubReference(f1), getFootballClubReference(f2),
                        Integer.parseInt(data[2]), Integer.parseInt(data[3]),
                        data[4], data[5], Integer.parseInt(data[6])));
                }
            }
        }

        return true;
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

    public static void loadTestData() {
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();

        loadFootballClubsFromFile(true);
        loadGroups(true);
        loadGroupMatches(true);
        loadFinalsMatches(true);
    }

}

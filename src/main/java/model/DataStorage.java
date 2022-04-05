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
    private static final String footballClubsPath = "src/main/resources/data/FootballClubs.csv";
    private static final String footballClubsTestPath = "src/main/resources/data/FootballClubsTest.csv";

    private static final String groupPath = "src/main/resources/data/GroupData.csv";
    private static final String groupPathTest = "src/main/resources/data/GroupDataTest.csv";

    private static final String groupMatchespath = "src/main/resources/data/groupMatches.csv";
    private static final String groupMatchespathtest = "src/main/resources/data/groupMatchesTest.csv";

    private static final String tournementFinalsPath = "src/main/resources/data/tournementFinals.csv";

    private static final String COMMA_DELIMITER = ",";


    /**
     * Method for saving football clubs to csv-file
     * Loops through the football clubs and adds the data to the cvs-file (path)
     * @param test boolean to not overwrite savefile
     *
     */
    public static void saveFootballClubs(boolean test) {
        StringBuilder data = new StringBuilder();
        for (FootballClub fc : GroupController.getInstance().getFootballClubs()) {
            data.append(fc.getCsvFormat() + "\n");
        }
        DataHandler.saveToFile(data.toString(), getFootballClubsPath(test));
    }

    /**
     * reads footballclubs from file, and creates array of them
     * @param test boolean to not overwrite savefile
     * @throws RuntimeException if footballclubs coud not be read from file
     */
    private static void loadFootballClubsFromFile(boolean test)throws RuntimeException{
        try {
            ArrayList<FootballClub> footballClubsFromFIle = new ArrayList<FootballClub>();
            Objects.requireNonNull(DataHandler.readFromFile(getFootballClubsPath(test))).forEach(e ->
                    footballClubsFromFIle.add(parseFootballClub(e)));

            GroupController.getInstance().addAll(footballClubsFromFIle);
        } catch (NullPointerException e){
            throw new RuntimeException("No data found");
        }
    }

    /**
     * returns the path for file reading later or for tests
     * as path
     * @param test if test or not (to not overide savefile
     * @return footballclubs path
     */
    private static Path getFootballClubsPath(boolean test){
        return Paths.get((test) ? footballClubsTestPath : footballClubsPath);
    }

    private static Path getGroupPath(boolean test){
        return Paths.get((test) ? groupPathTest : groupPath);
    }

    private static void saveGroupToFile(boolean test){
        DataHandler.saveToFile(GroupController.getInstance().getGroups().stream().map(Group::generateCsv)
                .collect(Collectors.joining("")), getGroupPath(test));

    }

    /**
     * loades grupes from file
     * @throws RuntimeException if gupeSaveFile coud not be read
     */
    private static void loadGrupes(boolean test) throws RuntimeException{
        try {
            Objects.requireNonNull(DataHandler.readFromFile(getGroupPath(test))).forEach(e -> {
                String[] data = e.split(";");
                Group tempGroup = new Group();
                tempGroup.setGroupNumber(Integer.parseInt(data[0]));
                for (int i = 1; i < data.length - 1; i++) {

                    tempGroup.addTeam(getFootballClubReference(parseFootballClub(data[i])));
                }
                tempGroup.setHasEnded(Boolean.parseBoolean(data[data.length-1]));
                GroupController.getInstance().getGroups().add(tempGroup);
            });
        }catch (NullPointerException e){
            throw new RuntimeException("cant read data from file");
        }
    }

    /**
     * creates football club from csv string
     * @param csvString to parse
     * @return Footballclub
     */
    private static FootballClub parseFootballClub(String csvString){
        String[] values = csvString.split(COMMA_DELIMITER);
        return new FootballClub(values[0],values[1], Integer.parseInt(values[2]),
                Integer.parseInt(values[3]), Integer.parseInt(values[4]));
    }


    /**
     * saves groupMatches to file
     * @param test if test uses different path
     */
    private static void saveGroupMatches(boolean test){
        try {
            DataHandler.saveToFile("", getGroupMatchesPath(test));

            DataHandler.saveToFile(TournamentManager.getInstance().listGroupMatches()
                            .stream().map(Match::getCsv)
                            .collect(Collectors.joining("\n"))
                    , getGroupMatchesPath(test));
        } catch (NullPointerException e){
            DataHandler.saveToFile("", getGroupMatchesPath(test));
        }
    }

    /**
     * load matches from file, and find grupe they exist inn
     * then add the match to the grupe
     * @param test if test
     */
    private static void loadGroupMatches(boolean test){
        ArrayList<Group> groups = GroupController.getInstance().getGroups();

        DataHandler.readFromFile(getGroupMatchesPath(test)).forEach(e->{
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
     * generates csv for all finals matches.
     * if no matches in bracket no matches will be written to file
     * separates the matches with bracket names ("roundOf32A") as named in TournamentManger class
     */
    public static void saveTournamentFinals(){

        DataHandler.saveToFile("", Paths.get(tournementFinalsPath));
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

        DataHandler.saveToFile(String.join("\n", dataToSave), Paths.get(tournementFinalsPath));
    }

    /**
     * Loads all tournament finals matches to correct arrays
     */
    public static boolean loadFinalsMatches(){
        TournamentManager tr = TournamentManager.getInstance();
        ArrayList<String> dataRead = DataHandler.readFromFile(Paths.get(tournementFinalsPath));

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
     * group matches path for the save file
     * @param test if test
     * @return path to file
     */
    private static Path getGroupMatchesPath(boolean test){
        return Paths.get((test) ? groupMatchespathtest : groupMatchespath);
    }

    /**
     * finds reference to footballclub in Grupecontroller instead of copy
     * @param f footballclub to find
     * @return found club
     * @throws RuntimeException if reference was not found
     */
    private static FootballClub getFootballClubReference(FootballClub f) throws RuntimeException{
        ArrayList<FootballClub> footballClubs = GroupController.getInstance().getFootballClubs();
        int index = footballClubs.indexOf(f);
        if (index != -1)
            return footballClubs.get(index);
        else throw new RuntimeException("cant find reference for club:" + f.toString());
    }






    /**
     * saves all data
     * @param test if test
     */
    public static void save(boolean test){
        saveTournamentFinals();
        saveFootballClubs(test);
        saveGroupToFile(test);
        saveGroupMatches(test);

    }

    /**
     * loades all data
     * @param test if test
     */
    public static void load(boolean test){
        GroupController.getInstance().resetList();
        TournamentManager.getInstance().resetAllLists();

        loadFootballClubsFromFile(test);
        loadGrupes(test);
        loadGroupMatches(test);
        loadFinalsMatches();
    }


}

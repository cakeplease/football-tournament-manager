package view;

import base.FootballClub;
import base.Group;
import base.Match;
import base.TournamentManager;
import controller.GUIController;
import controller.GroupController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * MatchesView class for showing all matches
 */
public class MatchesView extends View {
    protected VBox pane;
    protected VBox container;
    protected ScrollPane scrollPane;
    private ScreenController screenController;
    private Dialog dialog;
    Optional result;

    /**
     * MatchesView constructor
     * @param screenController ScreenController instance to use
     */
    public MatchesView(ScreenController screenController) {
        this.pane = new VBox();
        this.container = new VBox();
        this.scrollPane = new ScrollPane();
        this.screenController = screenController;
        this.setup();
    }

    /**
     * Gets pane
     * @return pane
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Shows all matches in tables, where when you double-click on a row you get a dialog pop up window where you can change match data
     */
    public void setup() {
        this.resetPane();
       container.setPadding(new Insets(25, 200, 25, 25));
        container.setSpacing(10);
        container.setId("background");
        container.setMinWidth(600);
        pane.setId("background");
        scrollPane.setId("background");

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        container.getChildren().add(backButton);


        Text sceneTitle = new Text("Matches (double click a match to edit)");
        sceneTitle.setId("header-text");
        container.getChildren().add(sceneTitle);

        GroupController groupController = GroupController.getInstance();
        TournamentManager tournamentManager = TournamentManager.getInstance();

        dialog = new Dialog();
        dialog.getDialogPane().setMinSize(550, 200);

        GridPane grid = new GridPane();
        grid.setMinSize(200, 200);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        final TextField time = new TextField();
        time.setMinSize(100, 20);
        time.setPromptText("Time");

        final TextField date = new TextField();
        date.setPromptText("Date");
        date.setMinSize(100, 20);

        final TextField fieldNr = new TextField();
        fieldNr.setPromptText("Field number");
        fieldNr.setMinSize(100, 20);

        final TextField score1 = new TextField();
        score1.setPromptText("Score team 1");
        score1.setMinSize(100, 20);

        final TextField score2 = new TextField();
        score2.setPromptText("Score team 2");
        score2.setMinSize(100, 20);

        Text timeText = new Text("Time:");
        timeText.setId("normal-text");
        grid.add(timeText, 0, 0);
        grid.add(time, 1, 0);

        Text dateText = new Text("Date:");
        dateText.setId("normal-text");
        grid.add(dateText, 0, 1);
        grid.add(date, 1, 1);

        Text fieldNrText = new Text("Field number:");
        fieldNrText.setId("normal-text");
        grid.add(fieldNrText, 0, 2);
        grid.add(fieldNr, 1, 2);

        Text score1Text = new Text("Score team 1: ");
        score1Text.setId("normal-text");
        grid.add(score1Text, 2, 0);
        grid.add(score1, 3, 0);

        Text score2Text = new Text("Score team 2: ");
        score2Text.setId("normal-text");
        grid.add(score2Text, 2, 1);
        grid.add(score2, 3, 1);

        dialog.getDialogPane().getChildren().add(grid);
        dialog.initOwner(FTApplication.primaryStage);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.isEmpty(time.textProperty())
                        .and(Bindings.isEmpty(date.textProperty()))
                        .and(Bindings.isEmpty(fieldNr.textProperty()))
                        .and(Bindings.isEmpty(score1.textProperty()))
                        .and(Bindings.isEmpty(score2.textProperty()))

        );

        ArrayList<Match> groupMatches = new ArrayList<>();
        for (Group group : groupController.getGroups()) {
            for (Match groupMatch : group.getGroupMatches()) {
                groupMatches.add(groupMatch);
            }
        }

        LinkedHashMap<String, ArrayList<Match>> allMatches = new LinkedHashMap();

        allMatches.put("Group stages", groupMatches);
        allMatches.put("Round of 32 A", tournamentManager.getRoundOf32A());
        allMatches.put("Round of 32 B", tournamentManager.getRoundOf32B());
        allMatches.put("Round of 16 A", tournamentManager.getRoundOf16A());
        allMatches.put("Round of 16 B", tournamentManager.getRoundOf16B());
        allMatches.put("Quarter finals A", tournamentManager.getQuarterFinalsA());
        allMatches.put("Quarter finals B", tournamentManager.getQuarterFinalsB());
        allMatches.put("Semi-finals A", tournamentManager.getSemifinalsA());
        allMatches.put("Semi-finals B", tournamentManager.getSemifinalsB());
        ArrayList<Match> finals = new ArrayList<>();
        if (!tournamentManager.getFinalA().isEmpty()) {
            finals.add(tournamentManager.getFinalA().get(0));
        }
        if (!tournamentManager.getFinalB().isEmpty()) {
            finals.add(tournamentManager.getFinalB().get(0));
        }
        allMatches.put("Finals matches", finals);

        allMatches.forEach((heading, matches) -> {

            //Table title
            Text tableHeading = new Text(heading);
            //tableHeading.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
            tableHeading.setId("header-text");
            container.getChildren().add(tableHeading);

            //Table
            TableView<Match> matchesTable = new TableView<Match>();

            //Table columns
            TableColumn<Match, String> team1 = new TableColumn<>("Team 1");
            team1.setCellValueFactory(new PropertyValueFactory<>("footballClub1"));
            TableColumn<Match, String> team2 = new TableColumn<>("Team 2");
            team2.setCellValueFactory(new PropertyValueFactory<>("footballClub2"));
            TableColumn<Match, String> scoreTeam1 = new TableColumn<>("Score team 1");
            scoreTeam1.setCellValueFactory(new PropertyValueFactory<>("score1"));
            TableColumn<Match, String> scoreTeam2 = new TableColumn<>("Score team 2");
            scoreTeam2.setCellValueFactory(new PropertyValueFactory<>("score2"));
            TableColumn<Match, String> winner = new TableColumn<>("Winner");
            winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
            TableColumn<Match, String> matchTime = new TableColumn<>("Time");
            matchTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            TableColumn<Match, String> matchDate = new TableColumn<>("Date");
            matchDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            TableColumn<Match, Integer> matchFieldNr = new TableColumn<>("Field nr");
            matchFieldNr.setCellValueFactory(new PropertyValueFactory<>("fieldNr"));

            //Double click to edit match
            matchesTable.setRowFactory(tv -> {
                TableRow<Match> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        time.clear();
                        date.clear();
                        fieldNr.clear();
                        score1.clear();
                        score2.clear();
                        result = dialog.showAndWait();

                        FootballClub footballClub1 = row.getItem().getFootballClub1();
                        FootballClub footballClub2 = row.getItem().getFootballClub2();
                        String editedTime = time.getText();
                        String editedDate = date.getText();
                        String editedFieldNr = fieldNr.getText();
                        String editedScore1 = score1.getText();
                        String editedScore2 = score2.getText();

                        if (result.get() == ButtonType.OK) {
                            for (Match m : matches) {
                                if (m.getFootballClub1().equals(footballClub1) && m.getFootballClub2().equals(footballClub2)) {
                                    GUIController.editMatch(footballClub1, footballClub2, editedTime, editedDate, editedFieldNr, editedScore1, editedScore2);
                                    if (GUIController.allMatchesHaveWinner(matches)) {
                                        if (tournamentManager.getRoundOf32A().isEmpty())
                                            tournamentManager.generateRoundOf32();
                                        else if (tournamentManager.getRoundOf16A().isEmpty())
                                            tournamentManager.generateRoundOf16();
                                        else if (tournamentManager.getQuarterFinalsA().isEmpty())
                                            tournamentManager.generateQuarterFinals();
                                        else if (tournamentManager.getSemifinalsA().isEmpty())
                                            tournamentManager.generateSemifinals();
                                        else if (tournamentManager.getFinalA().isEmpty()) {
                                            tournamentManager.generateFinalA();
                                        }
                                        else if (tournamentManager.getFinalB().isEmpty()){
                                            tournamentManager.generateFinalB();
                                        }
                                    }

                                    matchesTable.refresh();
                                    break;
                                }
                            }
                        }
                    }
                });
                return row;
            });

            matchesTable.getColumns().addAll(team1, team2, scoreTeam1, scoreTeam2, winner, matchTime, matchDate, matchFieldNr);

            for (Match match : matches) {
                matchesTable.getItems().add(match);
            }

            container.getChildren().add(matchesTable);

        });

        scrollPane.setFitToWidth(true);
        scrollPane.setContent(container);
        this.pane.getChildren().add(scrollPane);
    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.container.getChildren().clear();
        this.pane.getChildren().clear();
    }
}

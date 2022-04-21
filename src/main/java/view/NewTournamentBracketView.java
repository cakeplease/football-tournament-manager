package view;

import base.FootballClub;
import base.Group;
import base.Match;
import base.TournamentManager;
import controller.GroupController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * New tournament class without function to chose winner. This function will only be available under MatchesView
 */

public class NewTournamentBracketView extends View {
    protected static GridPane tournamentBracketPane;
    private ScreenController screenController;
    private Dialog dialog;
    private Optional result;
    private String team1String;
    private String team2String;
    private static StackPane stackPane;
    private ScrollPane scrollPane;
    private TournamentManager tournamentManager;


    public NewTournamentBracketView(ScreenController ScreenController) {
        this.tournamentBracketPane = new GridPane();
        this.stackPane = new StackPane();
        this.screenController = ScreenController;
        this.setup();
    }

    public Pane getPane() {
        return this.stackPane;
    }

    public void setup() {
        this.resetPane();
        this.scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        this.tournamentManager = TournamentManager.getInstance();

        scrollPane.setContent(tournamentBracketPane);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        tournamentBracketPane.add(backButton, 0, 0);
        tournamentBracketPane.setHgap(30);
        tournamentBracketPane.setVgap(8);
        tournamentBracketPane.setPadding(new Insets(25, 25, 25, 25));


        /*
         * Method to print out boxes to the tournament bracket. The for loop goes through every number in the
         * columnNumbers array and uses them as column numbers. Then it makes the boxes according to the
         * column number.
         */
        int[] columnNumbers = new int[]{0, 8, 1, 7, 2, 6, 3, 5, 4};

        for (int column : columnNumbers) {
            if (column == 0 || column == 8) {
                for (int row = 2; row < 17; row++) { //(int row = 2; row < 10; row++)
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);

                    tournamentBracketPane.add(textBox, column, row);
                    row++;
                }
            } else if (column == 1 || column == 7) {
                for (int row = 3; row < 16; row++) {
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);
                    tournamentBracketPane.add(textBox, column, row);
                    row += 3;
                }
            } else if (column == 2 || column == 6) {
                for (int row = 5; row < 14; row++) {
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);
                    tournamentBracketPane.add(textBox, column, row);
                    row += 7;
                }
            } else {
                int row = 9;
                Rectangle rect = new Rectangle(125, 50);
                rect.setFill(Color.WHITESMOKE);
                rect.setStroke(Color.BLACK);
                StackPane textBox = new StackPane();
                textBox.getChildren().add(rect);
                tournamentBracketPane.add(textBox, column, row);
            }
        }

        int teamsIndex = 0;

        GroupController groupController = GroupController.getInstance();
        ArrayList<Group> groups = groupController.getGroups();
        if (groups.size() == 16) tournamentManager.generateRoundOf32();

        if (tournamentManager.getRoundOf32A().size() == 16) {
            for (int column = 0; 9 > column; column++) {
                for (int row = 2; row < 17; row++) {
                    FootballClub team1 = tournamentManager.getRoundOf32A().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getRoundOf32A().get(teamsIndex).getFootballClub1();
                    Match currentMatch = tournamentManager.getRoundOf32A().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }

                    team1Label.setFont(Font.font("Verdana", 11));
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFont(Font.font("Verdana", 11));
                    team2Label.setFill(Color.BLACK);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().addAll();
                    row++;
                    teamsIndex++;
                }
                column += 7;
            }
        }

        stackPane.getChildren().addAll(scrollPane, tournamentBracketPane);

    }

    @Override
    void resetPane() {
        this.tournamentBracketPane.getChildren().clear();
        if(!(this.stackPane == null)) this.stackPane.getChildren().clear();
    }


    static Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if((gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column)) {
                result = node;
                break;
            }
        }

        return result;
    }

}

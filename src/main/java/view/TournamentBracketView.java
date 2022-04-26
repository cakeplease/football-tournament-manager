package view;

import base.FootballClub;
import base.Group;
import base.Match;
import base.TournamentManager;
import controller.GroupController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public class TournamentBracketView extends View {
    protected static GridPane tournamentBracketPane;
    private ScreenController screenController;
    private Dialog dialog;
    private Optional result;
    private String team1String;
    private String team2String;
    private static StackPane stackPane;
    private ScrollPane scrollPane;
    private TournamentManager tournamentManager;


    public TournamentBracketView(ScreenController ScreenController) {
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
        tournamentBracketPane.setVgap(10); //8
        tournamentBracketPane.setPadding(new Insets(25, 25, 25, 25));

        Text aBracket = new Text("Bracket A");
        aBracket.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        tournamentBracketPane.add(aBracket, 0, 1);
        Text bBracket = new Text("Bracket B");
        bBracket.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        VBox bBox = new VBox();
        bBox.getChildren().add(bBracket);
        bBox.setMinHeight(125);
        bBox.setAlignment(Pos.BOTTOM_LEFT);
        tournamentBracketPane.add(bBox, 0, 17);



        /*
         * Method to print out boxes to the tournament bracket. The for loop goes through every number in the
         * columnNumbers array and uses them as column numbers. Then it makes the boxes according to the
         * column number.
         */
        int[] columnNumbers = new int[]{0, 8, 1, 7, 2, 6, 3, 5, 4};

        for (int column : columnNumbers) {
            if (column == 0 || column == 8) {
                for (int row = 2; row < 33; row++) { //(int row = 2; row < 17; row++)
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);

                    tournamentBracketPane.add(textBox, column, row);
                    row++;
                    if (row == 16) row++; //gap between A and B brackets

                }
            } else if (column == 1 || column == 7) {
                for (int row = 3; row < 33; row++) {
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);
                    tournamentBracketPane.add(textBox, column, row);
                    row += 3;
                    if (row == 15) row += 3; // gap between A and B brackets
                }
            } else if (column == 2 || column == 6) {
                for (int row = 5; row < 36; row++) {
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);
                    tournamentBracketPane.add(textBox, column, row);
                    row += 7;
                    if (row == 13) row += 7; // gap between A and B brackets

                }
            } else {
                for (int row = 9; row < 26; row++) {
                    Rectangle rect = new Rectangle(125, 50);
                    rect.setFill(Color.WHITESMOKE);
                    rect.setStroke(Color.BLACK);
                    StackPane textBox = new StackPane();
                    textBox.getChildren().add(rect);
                    tournamentBracketPane.add(textBox, column, row);
                    row = (row * 2) + 6;
                }
            }
        }

        int teamsIndex = 0;

/*        GroupController groupController = GroupController.getInstance();
        ArrayList<Group> groups = groupController.getGroups();
        if (groups.size() == 16) tournamentManager.generateRoundOf32();*/

        if (tournamentManager.getRoundOf32A().size() == 16) {
            for (int column = 0; column < 9; column++) {
                for (int row = 2; row < 17; row++) {
                    FootballClub team1 = tournamentManager.getRoundOf32A().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getRoundOf32A().get(teamsIndex).getFootballClub2();
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
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row++;
                    teamsIndex++;
                }
                column += 7;
            }
        }

        teamsIndex = 0;
        if (tournamentManager.getRoundOf32B().size() == 16) {
            for (int column = 0; column < 9; column++) {
                for (int row = 18; row < 33; row++) {
                    FootballClub team1 = tournamentManager.getRoundOf32B().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getRoundOf32B().get(teamsIndex).getFootballClub2();
                    Match currentMatch = tournamentManager.getRoundOf32B().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row++;
                    teamsIndex++;
                }
                column += 7;
            }
        }


        teamsIndex = 0;
        if (tournamentManager.getRoundOf16A().size() == 8) {
            for (int column = 1; column < 8; column++) {
                for (int row = 3; row < 16; row++) {
                    FootballClub team1 = tournamentManager.getRoundOf16A().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getRoundOf16A().get(teamsIndex).getFootballClub2();
                    Match currentMatch = tournamentManager.getRoundOf16A().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row += 3;
                    teamsIndex++;
                }
                column += 5;
            }
        }

        teamsIndex = 0;
        if (tournamentManager.getRoundOf16B().size() == 8) {
            for (int column = 1; column < 8; column++) {
                for (int row = 19; row < 33; row++) {
                    FootballClub team1 = tournamentManager.getRoundOf16B().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getRoundOf16B().get(teamsIndex).getFootballClub2();
                    Match currentMatch = tournamentManager.getRoundOf16B().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row += 3;
                    teamsIndex++;
                }
                column += 5;
            }
        }

        teamsIndex = 0;
        if (tournamentManager.getQuarterFinalsA().size() == 4) {
            for (int column = 2; column < 7; column++) {
                for (int row = 5; row < 14; row++) {
                    FootballClub team1 = tournamentManager.getQuarterFinalsA().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getQuarterFinalsA().get(teamsIndex).getFootballClub2();
                    Match currentMatch = tournamentManager.getQuarterFinalsA().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row += 7;
                    teamsIndex++;
                }
                column += 3;
            }
        }

        teamsIndex = 0;
        if (tournamentManager.getQuarterFinalsB().size() == 4) {
            for (int column = 2; column < 7; column++) {
                for (int row = 21; row < 36; row++) {
                    FootballClub team1 = tournamentManager.getQuarterFinalsB().get(teamsIndex).getFootballClub1();
                    FootballClub team2 = tournamentManager.getQuarterFinalsB().get(teamsIndex).getFootballClub2();
                    Match currentMatch = tournamentManager.getQuarterFinalsB().get(teamsIndex);
                    Text team1Label = new Text(team1.toString());
                    Text team2Label = new Text(team2.toString());

                    if (currentMatch.getWinner() != null) {
                        if (currentMatch.getWinner().equals(team1)) {
                            team1Label.setStyle("-fx-font-weight: bold");
                        } else if (currentMatch.getWinner().equals(team2)) {
                            team2Label.setStyle("-fx-font-weight: bold");
                        }
                    }
                    VBox teamLabelContainer = new VBox();
                    team1Label.setFill(Color.BLACK);

                    team2Label.setFill(Color.BLACK);

                    teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                    //TODO put these two labels into one text to keep styling
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(teamLabelContainer);
                    row += 7;
                    teamsIndex++;
                }
                column += 3;
            }
        }


        teamsIndex = 0;
        if (tournamentManager.getSemifinalsA().size() == 2) {
            for (int column = 3; column < 6; column++) {
                int row = 9;
                FootballClub team1 = tournamentManager.getSemifinalsA().get(teamsIndex).getFootballClub1();
                FootballClub team2 = tournamentManager.getSemifinalsA().get(teamsIndex).getFootballClub2();
                Match currentMatch = tournamentManager.getSemifinalsA().get(teamsIndex);
                Text team1Label = new Text(team1.toString());
                Text team2Label = new Text(team2.toString());

                if (currentMatch.getWinner() != null) {
                    if (currentMatch.getWinner().equals(team1)) {
                        team1Label.setStyle("-fx-font-weight: bold");
                    } else if (currentMatch.getWinner().equals(team2)) {
                        team2Label.setStyle("-fx-font-weight: bold");
                    }
                }
                VBox teamLabelContainer = new VBox();
                team1Label.setFill(Color.BLACK);

                team2Label.setFill(Color.BLACK);

                teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                //TODO put these two labels into one text to keep styling
                StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                textBox.getChildren().add(teamLabelContainer);
                teamsIndex++;
                column++;
            }
        }

        teamsIndex = 0;
        if (tournamentManager.getSemifinalsB().size() == 2) {
            for (int column = 3; column < 6; column++) {
                int row = 25;
                FootballClub team1 = tournamentManager.getSemifinalsB().get(teamsIndex).getFootballClub1();
                FootballClub team2 = tournamentManager.getSemifinalsB().get(teamsIndex).getFootballClub2();
                Match currentMatch = tournamentManager.getSemifinalsB().get(teamsIndex);
                Text team1Label = new Text(team1.toString());
                Text team2Label = new Text(team2.toString());

                if (currentMatch.getWinner() != null) {
                    if (currentMatch.getWinner().equals(team1)) {
                        team1Label.setStyle("-fx-font-weight: bold");
                    } else if (currentMatch.getWinner().equals(team2)) {
                        team2Label.setStyle("-fx-font-weight: bold");
                    }
                }
                VBox teamLabelContainer = new VBox();
                team1Label.setFill(Color.BLACK);

                team2Label.setFill(Color.BLACK);

                teamLabelContainer.getChildren().addAll(team1Label, team2Label);

                //TODO put these two labels into one text to keep styling
                StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                textBox.getChildren().add(teamLabelContainer);
                teamsIndex++;
                column++;
            }
        }

        if (!tournamentManager.getFinalA().isEmpty()) {
            Match currentMatch = tournamentManager.getFinalA().get(0);
            FootballClub team1 = currentMatch.getFootballClub1();
            FootballClub team2 = currentMatch.getFootballClub2();

            Text team1Label = new Text(team1.toString());
            Text team2Label = new Text(team2.toString());

            if (currentMatch.getWinner() != null) {
                if (currentMatch.getWinner().equals(team1)) {
                    team1Label.setStyle("-fx-font-weight: bold");
                } else if (currentMatch.getWinner().equals(team2)) {
                    team2Label.setStyle("-fx-font-weight: bold");
                }
            }
            VBox teamLabelContainer = new VBox();
            team1Label.setFill(Color.BLACK);

            team2Label.setFill(Color.BLACK);

            teamLabelContainer.getChildren().addAll(team1Label, team2Label);

            StackPane textBox = (StackPane) getNodeByRowColumnIndex(9, 4, tournamentBracketPane);
            textBox.getChildren().add(teamLabelContainer);

        }

        if (!tournamentManager.getFinalB().isEmpty()) {
            Match currentMatch = tournamentManager.getFinalB().get(0);
            FootballClub team1 = currentMatch.getFootballClub1();
            FootballClub team2 = currentMatch.getFootballClub2();

            Text team1Label = new Text(team1.toString());
            Text team2Label = new Text(team2.toString());

            if (currentMatch.getWinner() != null) {
                if (currentMatch.getWinner().equals(team1)) {
                    team1Label.setStyle("-fx-font-weight: bold");
                } else if (currentMatch.getWinner().equals(team2)) {
                    team2Label.setStyle("-fx-font-weight: bold");
                }
            }
            VBox teamLabelContainer = new VBox();
            team1Label.setFill(Color.BLACK);

            team2Label.setFill(Color.BLACK);

            teamLabelContainer.getChildren().addAll(team1Label, team2Label);

            StackPane textBox = (StackPane) getNodeByRowColumnIndex(25, 4, tournamentBracketPane);
            textBox.getChildren().add(teamLabelContainer);

        }

        stackPane.getChildren().addAll(scrollPane, tournamentBracketPane);
    }


    @Override
    void resetPane() {
        this.tournamentBracketPane.getChildren().clear();
        if (!(this.stackPane == null)) this.stackPane.getChildren().clear();
    }


    static Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if ((gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column)) {
                result = node;
                break;
            }
        }

        return result;
    }

}

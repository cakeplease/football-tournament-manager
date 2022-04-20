package view;

import base.Group;
import base.Match;
import base.TournamentManager;
import controller.GroupController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;


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
        //this.stackPane = new StackPane();
        //tournamentBracketPane.setMinSize(3000, 3000);
        this.scrollPane = new ScrollPane();
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

        PhongMaterial grey = new PhongMaterial();
        grey.setDiffuseColor(Color.DARKGREY);
        grey.setSpecularColor(Color.BLACK);


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

                    int finalRow = row; //for lambda expression
                    textBox.setOnMouseClicked(e -> {
                        StackPane textBox2 = (StackPane) getNodeByRowColumnIndex(finalRow, column, this.tournamentBracketPane);
                        Text teamText2 = (Text) textBox2.getChildren().get(1);
                        String[] teamsList = teamText2.getText().split("\n");
                        team1String = teamsList[0];
                        team2String = teamsList[1];

                        result = createWinnerSelectDialog(team1String, team2String, finalRow, column).showAndWait();
                        //if (team1.isPressed() || team2.isPressed()) dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);


                    });
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
        //DET VAR TIDLIGERE Hgap(60) OG HORISONTAL LENGDE PÅ BOKSEN 100.0


        /**
         * GROUPS
         */

        int teams = 0;
/*        if (getStartingTeams().size() == 32) {
            for (int column = 0; 9 > column; column++) {
                for (int row = 2; row < 17; row++) {
                    Text text = new Text(getStartingTeams().get(teams));
                    text.setFont(Font.font("Verdana", 11));
                    text.setFill(Color.BLACK);
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(text);
                    row++;
                    teams++;
                }
                column += 7;
            }
        }*/
        if (getStartingTeams().size() != 0) tournamentManager.generateRoundOf32();

        if (tournamentManager.getRoundOf32A().size() == 16) {
            for (int column = 0; 9 > column; column++) {
                for (int row = 2; row < 17; row++) {
                    Text text = new Text(tournamentManager.getRoundOf32A().get(teams).toString());
                    System.out.println(text);
                    text.setFont(Font.font("Verdana", 11));
                    text.setFill(Color.BLACK);
                    StackPane textBox = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    textBox.getChildren().add(text);
                    row++;
                    teams++;
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

    public ArrayList<String> getStartingTeams() {
        //DataStorage.load(true);//TODO dette er bare en test
        GroupController groupController = GroupController.getInstance();

        ArrayList<Group> groups = groupController.getGroups();
        ArrayList<String> strings = new ArrayList<>();


        for (Group group : groups) {
            strings.add(group.getGroupTeams().get(0).getName() + "\n" + group.getGroupTeams().get(1).getName());
            strings.add(group.getGroupTeams().get(2).getName() + "\n" + group.getGroupTeams().get(3).getName());
        }

        return strings;
    }

    static Dialog createWinnerSelectDialog(String team1String, String team2String, int row, int column) {

        TournamentManager tournamentManager = TournamentManager.getInstance();

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setMinSize(400, 200);
        dialog.initOwner(FTApplication.primaryStage);

        GridPane dialogGrid = new GridPane();
        dialogGrid.setMinSize(200, 200);
        dialogGrid.setHgap(10);
        dialogGrid.setVgap(5);
        dialogGrid.setPadding(new Insets(10, 10, 10, 10));
        Text selectWinner = new Text("Select winner");
        selectWinner.setFont(new Font(20));
        dialogGrid.add(selectWinner, 0, 1);
        RadioButton team1 = new RadioButton();
        team1.setText(team1String);
        team1.setMinSize(200,50);
        RadioButton team2 = new RadioButton();
        team2.setText(team2String);
        team2.setMinSize(200,50);

        ToggleGroup buttonGroup = new ToggleGroup();
        team1.setToggleGroup(buttonGroup);
        team2.setToggleGroup(buttonGroup);

        buttonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {

                RadioButton rb = (RadioButton)buttonGroup.getSelectedToggle();

                if (rb != null) {
                    StackPane thisStackPane = (StackPane) getNodeByRowColumnIndex(row, column, tournamentBracketPane);
                    Text text = (Text) thisStackPane.getChildren().get(thisStackPane.getChildren().size()-1);

                    for (Match m : tournamentManager.getRoundOf32A()){
                        if (rb.getText().equalsIgnoreCase(m.getFootballClub1().getName())){
                            m.setWinner(m.getFootballClub1());

                            String[] splitText = text.getText().split("\n");

                            TextFlow flow = new TextFlow();

                            Text text1 = new Text(splitText[0] + "\n");
                            text1.setStyle("-fx-font-weight: bold");

                            Text text2=new Text(splitText[1]);
                            text2.setStyle("-fx-font-weight: regular");

                            flow.getChildren().addAll(text1, text2);

                            text.setText("");
                            flow.setTextAlignment(TextAlignment.CENTER);
                            flow.setPadding(new Insets(10, 10, 10, 10));
                            thisStackPane.getChildren().add(flow);

                        }

                        else if (rb.getText().equalsIgnoreCase(m.getFootballClub2().getName())){
                            m.setWinner(m.getFootballClub2());

                            if (thisStackPane.getChildren().get(thisStackPane.getChildren().size()-1) instanceof TextFlow){
                                thisStackPane.getChildren().remove(thisStackPane.getChildren().size()-1);
                            }

                            String[] splitText = text.getText().split("\n");

                            TextFlow flow = new TextFlow();

                            Text text1 = new Text(splitText[0] + "\n");
                            text1.setStyle("-fx-font-weight: regular");

                            Text text2=new Text(splitText[1]);
                            text2.setStyle("-fx-font-weight: bold");

                            flow.getChildren().addAll(text1, text2);

                            text.setText("");
                            flow.setTextAlignment(TextAlignment.CENTER);
                            flow.setPadding(new Insets(10, 10, 10, 10));
                            thisStackPane.getChildren().add(flow);

                        }

                    }
                }
            }
        });

        dialogGrid.add(team1, 0, 4);
        dialogGrid.add(team2, 0, 5);


        dialog.getDialogPane().getChildren().add(dialogGrid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        return dialog;
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

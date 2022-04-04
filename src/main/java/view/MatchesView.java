package view;

import base.Match;
import base.TournamentManager;
import controller.GroupController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.util.ArrayList;
import java.util.Optional;


public class MatchesView extends View {
    protected VBox pane;
    private ScreenController screenController;
    private Dialog dialog;
    private String timeResult;
    private String fieldNrResult;
    private String dateResult;
    Optional result;
    private String score1Result;
    private String score2Result;

    public MatchesView(ScreenController screenController) {
        this.pane = new VBox();
        this.screenController = screenController;
        this.setup();
    }

    @Override
    Pane getPane() {
        return this.pane;
    }

    public void setup() {
        pane.getChildren().clear();
        pane.setPadding(new Insets(25,25,25,25));

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.getChildren().add(backButton);

        Text sceneTitle = new Text("Matches");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        //pane.add(sceneTitle, 1, 2);
        pane.getChildren().add(sceneTitle);


        GroupController groupController = GroupController.getInstance();
        TournamentManager tournamentManager = TournamentManager.getInstance();


        dialog = new Dialog();
        dialog.getDialogPane().setMinSize(400, 200);

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

        grid.add(new Text("Time:"), 0, 0);
        grid.add(time, 1, 0);
        grid.add(new Text("Date:"), 0, 1);
        grid.add(date, 1, 1);
        grid.add(new Text("Field number:"), 0, 2);
        grid.add(fieldNr, 1, 2);

        grid.add(new Text ("Score team 1: "), 2, 0);
        grid.add(score1, 3, 0);
        grid.add(new Text ("Score team 2: "), 2, 1);
        grid.add(score2, 3, 1);

        dialog.getDialogPane().getChildren().add(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        dialog.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
        dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.isEmpty(time.textProperty())
                        .and(Bindings.isEmpty(date.textProperty()))
                        .and(Bindings.isEmpty(fieldNr.textProperty()))
                        .and(Bindings.isEmpty(score1.textProperty()))
                        .and(Bindings.isEmpty(score2.textProperty()))

        );

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.setOnAction(e -> {
            result = dialog.showAndWait();
            if (result.get() == ButtonType.OK) {
                timeResult = time.getText();
                dateResult = date.getText();
                fieldNrResult = fieldNr.getText();
                score1Result = score1.getText();
                score2Result = score2.getText();
            }
        });

        pane.getChildren().add(editButton);

        TableView<String[]> table = new TableView<>();
        ArrayList<String> columnNames = new ArrayList<String>();
        columnNames.add("Football Club");
        columnNames.add("Football Club");
        columnNames.add("Time and date");
        columnNames.add("Field nr");
        columnNames.add("Score");

        if (!groupController.getGroups().isEmpty() && !tournamentManager.listGroupMatches().isEmpty()) {
            for (int i = 0; i < columnNames.size(); i++) {
                final int index = i;
                TableColumn<String[], String> column = new TableColumn<>(columnNames.get(i));
                //column.setCellValueFactory(cd -> new PropertyValueFactory());
                column.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue()[index]));


                table.getColumns().add(column);
            }
            String[] userInputs = new String[5];
            table.getItems().add(userInputs);

        }
        pane.getChildren().add(table);
    }
}

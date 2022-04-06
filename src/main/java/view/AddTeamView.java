package view;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

public class AddTeamView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    public AddTeamView(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }


    public Pane getPane() {
        return this.pane;
    }

    public void setup() {
        this.resetPane();
        pane.setPadding(new Insets(25,25,25,25));
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Add team");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        pane.add(sceneTitle, 0, 2);

        Label teamName = new Label("Team name:");
        pane.add(teamName, 0, 3);

        TextField teamNameField = new TextField();
        pane.add(teamNameField, 1, 3);

        Label nationalityLabel = new Label("Nationality:");
        pane.add(nationalityLabel, 0, 4);

        TextField nationalityField = new TextField();
        pane.add(nationalityField, 1, 4);

        Text actionTarget = new Text();
        pane.add(actionTarget, 0, 15);

        Button addTeamButton = new Button("Add team");
        addTeamButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                GUIController.addTeam(teamNameField.getText(), nationalityField.getText());
                actionTarget.setText("Team added");
            }
        });

        pane.add(addTeamButton, 1, 6);
    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

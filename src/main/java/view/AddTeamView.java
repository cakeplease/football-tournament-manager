package view;

import controller.GUIController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

/**
 * AddTeamView for adding teams
 */
public class AddTeamView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    /**
     * AddTeamView constructor
     * @param screenController ScreenController object
     */
    public AddTeamView(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    /**
     * Gets pane
     * @return Pane instance
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Sets up back button, title, fields for name and nationality and add button
     */
    public void setup() {
        this.resetPane();
        pane.setPadding(new Insets(25,25,25,25));
        pane.setHgap(10);
        pane.setVgap(10);
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Add team");
        sceneTitle.setId("header-text");
        pane.add(sceneTitle, 0, 2);

        Label teamName = new Label("Team name:");
        pane.add(teamName, 0, 3);

        TextField teamNameField = new TextField();
        teamNameField.setMaxWidth(200);
        teamNameField.setMaxHeight(20);
        pane.add(teamNameField, 1, 3);

        Label nationalityLabel = new Label("Nationality:");
        pane.add(nationalityLabel, 0, 4);

        TextField nationalityField = new TextField();
        nationalityField.setMaxWidth(200);
        pane.add(nationalityField, 1, 4);

        Text actionTarget = new Text();
        actionTarget.setId("feedback-text");
        VBox actionBox = new VBox();
        actionBox.getChildren().add(actionTarget);
        actionBox.setMinWidth(500);
        //pane.add(actionTarget, 0, 15);
        pane.add(actionBox, 1, 5);

        Button addTeamButton = new Button("Add team");
        addTeamButton.setOnAction(e -> {
            String feedback = GUIController.addTeam(teamNameField.getText(), nationalityField.getText());
            actionTarget.setText(feedback);
            teamNameField.clear();
            nationalityField.clear();
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

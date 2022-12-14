package view;

import controller.GUIController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

/**
 * NoGroupsView for situation when no groups are generated.
 */
public class NoGroupsView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    /**
     * NoGroupsView constructor
     * @param screenController ScreenController instance to use
     */
    public NoGroupsView(ScreenController screenController) {
        this.pane = new GridPane();
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
     * Shows back button, title, a text with instructions and a button for generating groups
     */
    public void setup() {
        this.resetPane();

        pane.setPadding(new Insets(25,25,25,25));
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.add(backButton, 0,1);

        Button generateGroups = new Button();
        generateGroups.setText("Generate groups");

        Text errorFeedback = new Text("");
        errorFeedback.setId("feedback-text");

        generateGroups.setOnAction(e -> {
            if (GUIController.generateGroups()) {
                screenController.activate("Groups");
            } else {
                errorFeedback.setText("There need to be 64 teams in order to generate groups.");
            }
        });
        pane.add(generateGroups, 20,1);

        Text sceneTitle = new Text("Groups");
        sceneTitle.setId("header-text");
        pane.add(sceneTitle, 1, 2);

        Text noGroupsText = new Text("No groups available. Press the button in the right corner to generate groups.");
        noGroupsText.setId("normal-text");
        pane.add(noGroupsText, 1, 4);
        pane.add(errorFeedback, 1, 5);

    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

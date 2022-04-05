package view;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

/**
 * View for situation when no groups exists.
 */
public class NoGroupsView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    public NoGroupsView(ScreenController screenController) {
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

        Button generateGroups = new Button();
        generateGroups.setText("Generate groups");
        generateGroups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                GUIController.generateGroups();
                screenController.activate("Groups");

            }
        });
        pane.add(generateGroups, 20,1);

        Text sceneTitle = new Text("Groups");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 1, 2);

        Text noGroupsText = new Text("No groups available. Press the button in the right corner to generate groups.");
        pane.add(noGroupsText, 1, 4);

    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

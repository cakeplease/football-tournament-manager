package view;

import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

/**
 * TeamsView class
 * Shows all teams
 */
public class TeamsView extends View {
    protected GridPane pane;
    private ScreenController screenController;
    GroupController groupController = GroupController.getInstance();

    /**
     * TeamsView constructor
     * @param screenController ScreenController instance to use
     */
    public TeamsView(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    /**
     * Gets pane
     * @return instance of pane
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Sets up the pane with the back button, a title and columns with the teams
     */
    public void setup() {
        this.resetPane();

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Teams");
        //sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        sceneTitle.setId("header-text");
        pane.add(sceneTitle, 0, 2);

        pane.setHgap(250);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));

        for (int i = 0; i < groupController.getFootballClubs().size(); i++) {
            Text text = new Text(groupController.getFootballClubs().get(i).getName());
            text.setId("normal-text");
            pane.add(text, i%4, (int)(i/4) + 5);
        }

    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

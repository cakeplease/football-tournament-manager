package view;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * View for situation when no groups exists.
 */
public class NoGroupsView {
    //Change variable name to [viewname]Pane
    protected GridPane noGroupsPane;
    private ScreenController screenController;

    public NoGroupsView(ScreenController screenController) {
        this.noGroupsPane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        noGroupsPane.add(backButton, 0,1);

        Text sceneTitle = new Text("Groups");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        noGroupsPane.add(sceneTitle, 1, 2);

        Text noGroupsText = new Text("No groups available. Press the button in the right corner to generate groups.");
        noGroupsPane.add(noGroupsText, 1, 4);

    }
}

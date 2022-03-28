package view;

import controller.GroupController;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;


public class MatchesView {
    protected GridPane pane;
    private ScreenController screenController;

    public MatchesView(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Matches");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 1, 2);


        GroupController groupController = GroupController.getInstance();


    }
}

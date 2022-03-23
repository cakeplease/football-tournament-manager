package view;

import base.Group;
import controller.GroupController;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * TeamsView class
 */
public class TeamsView {
    protected GridPane teamsViewPane;
    private ScreenController screenController;
    //TODO: bytt ut denne med singleton
    GroupController groupController = GroupController.getInstance();


    public TeamsView(ScreenController screenController) {
        this.teamsViewPane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        teamsViewPane.add(backButton, 0,1);

        Text sceneTitle = new Text("Teams");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        teamsViewPane.add(sceneTitle, 1, 2);

        //22, 22, 16


        for (int i = 0; i < groupController.getFootballClubs().size(); i++){
            for (int j = 0; j < groupController.getFootballClubs().size()/3; j++){
                for (int k = 0; k < 3; k++){
                    teamsViewPane.add(new Text(groupController.getFootballClubs().get(i).getName()), k, j);

                }
            }
        }

    }
}

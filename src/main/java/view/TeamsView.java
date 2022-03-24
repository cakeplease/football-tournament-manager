package view;

import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.*;

/**
 * TeamsView class
 */
public class TeamsView extends View {
    protected GridPane pane;
    private ScreenController screenController;
    //TODO: bytt ut denne med singleton
    GroupController groupController = GroupController.getInstance();



    public TeamsView(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public Pane getPane() {
        return this.pane;
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
<<<<<<< HEAD
        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Teams");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 1, 2);
=======
        teamsViewPane.add(backButton, 0, 1);

        Text sceneTitle = new Text("Teams");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        teamsViewPane.add(sceneTitle, 1, 2);
        teamsViewPane.add(sceneTitle, 0, 2);

        teamsViewPane.setHgap(250);
        teamsViewPane.setVgap(10);
        teamsViewPane.setPadding(new Insets(25, 25, 25, 25));

>>>>>>> 7ffa4388cbf00c0912bad127a783752c881b2440

        //22, 22, 16


<<<<<<< HEAD
        for (int i = 0; i < groupController.getFootballClubs().size(); i++){
            for (int j = 0; j < groupController.getFootballClubs().size()/3; j++){
                for (int k = 0; k < 3; k++){
                    pane.add(new Text(groupController.getFootballClubs().get(i).getName()), j, k);
=======
        for (int i = 0; i < groupController.getFootballClubs().size(); i++) {
            for (int j = 0; j < groupController.getFootballClubs().size() / 3; j++) {
                for (int k = 0; k < 3; k++) {
                    Text text = new Text(groupController.getFootballClubs().get(i).getName());
                    text.setFont(Font.font("Verdana", 20));
                    teamsViewPane.add(text, k, j + 5);
                }
            }
        }
    }
}


        //for testing layout without any teams
/*        for (int i = 0; i < 64; i++){
            for (int j = 0; j < 64/3; j++){
                for (int k = 0; k < 3; k++){
                    Text text = new Text("hei");
                    text.setFont(Font.font ("Verdana", 20));
                    teamsViewPane.add(text, k, j+5);
>>>>>>> 7ffa4388cbf00c0912bad127a783752c881b2440
                }
            }
        }

    }
*/

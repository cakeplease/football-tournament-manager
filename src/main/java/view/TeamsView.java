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

        pane.add(backButton, 0,1);


        Text sceneTitle = new Text("Teams");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 1, 2);


        pane.setHgap(250);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));



        //22, 22, 16


        for (int i = 0; i < groupController.getFootballClubs().size(); i++) {
            for (int j = 0; j < groupController.getFootballClubs().size() / 3; j++) {
                for (int k = 0; k < 3; k++) {
                    Text text = new Text(groupController.getFootballClubs().get(i).getName());
                    text.setFont(Font.font("Verdana", 20));
                    pane.add(text, k, j + 5);
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
                }
            }
        }

    }
*/

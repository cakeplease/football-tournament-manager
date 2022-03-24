package view;

import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GroupsView {
    protected GridPane groupsPane;
    private ScreenController screenController;

    public GroupsView(ScreenController ScreenController) {
        this.groupsPane = new GridPane();
        this.screenController = ScreenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        //backButton.setLayoutX(25);
        //backButton.setLayoutY(25);
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        Text t = new Text();
        t.setText("Show groups");
        t.setFont(Font.font ("Verdana", 30));
        //t.setFont(Font.font(30));
        //t.setFill(Color.RED);
        //t.setLayoutX(25);
        //t.setLayoutY(100);

        groupsPane.add(backButton, 0, 0);
        groupsPane.add(t, 0, 1);

        groupsPane.setHgap(250);
        groupsPane.setVgap(10);
        groupsPane.setPadding(new Insets(25,25,25,25));


        for (int i = 0; i < getAllGroups().size(); i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 3; l < 8; l++) {
                    Text text = new Text(getAllGroups().get(i));
                    text.setFont(Font.font ("Verdana", 20));
                    groupsPane.add(text, j, l);
                    //groupsPane.add(new Text(getAllGroups().get(i)), j, l);
                }
            }
        }

/*
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 3; l < 8; l++) {
                    Text text = new Text("yeee\nyeee\nyeee\nyeee");
                    text.setFont(Font.font ("Verdana", 20));
                    groupsPane.add(text, j, l);
                }
            }
        }*/
    }


    /**
     * Takes the list of the groups and puts them in a list of strings
     */

    public ArrayList<String> getAllGroups(){
        GroupController groupController = GroupController.getInstance();

        ArrayList<Group> groups = groupController.getGroups();
        ArrayList<String> strings = new ArrayList<>();


        for (Group group : groups) {
            String singleGroupString = "";
            for (int j = 0; j < 4; j++) {
                singleGroupString += group.getGroupTeams().get(j) + "\n";
            }
            strings.add(singleGroupString);
        }
        return strings;
    }
}

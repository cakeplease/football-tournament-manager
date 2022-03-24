package view;

import base.FootballClub;
import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class GroupsView extends View {
    protected GridPane pane;
    private ScreenController screenController;

    public GroupsView(ScreenController ScreenController) {
        this.pane = new GridPane();
        this.screenController = ScreenController;

        this.setup();
    }

    public Pane getPane() {
        return this.pane;
    }

    public void setup() {
        System.out.println("Setup i groupsView kjøres");
        GroupController groupController = GroupController.getInstance();

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        Text t = new Text();
        t.setText("Show groups");
        t.setFont(Font.font ("Verdana", 30));
        pane.add(backButton, 0, 0);
        pane.add(t, 0, 1);
        pane.setHgap(150);
        pane.setVgap(10);
        pane.setPadding(new Insets(25,25,25,25));

/*
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 3; l < 8; l++) {
                    Text text = new Text("yeee\nyeee\nyeee\nyeee");
                    text.setFont(Font.font ("Verdana", 20));
                    groupsPane.add(text, j, l);
                }
            }
        }



        //TODO fix the numbers
        for (int i = 0; i < groupController.getGroups().size(); i++) {
            for (int columns = 0; columns < 4; columns++) {
                for (int rows = 4; rows < 8; rows++) {
                    Text text = new Text(getAllGroups().get(1));//TODO fix the correct number
                    text.setFont(Font.font ("Verdana", 20));
                    pane.add(text, columns, rows);
                }
            }
        }
        */



    }


    /**
     * Takes the list of the groups and puts them in a list of strings
     */

    public ArrayList<String> getAllGroups(){
        GroupController groupController = GroupController.getInstance();

        ArrayList<Group> groups = groupController.getGroups();
        System.out.println(groups);
        ArrayList<String> strings = new ArrayList<>();


        for (Group group : groups) {
            String singleGroupString = "";
            for (int j = 0; j < 4; j++) {
                singleGroupString += group.getGroupTeams().get(j).getName() + "\n";
            }
            strings.add(singleGroupString);
        }
        return strings;
    }
}

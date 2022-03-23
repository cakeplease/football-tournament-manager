package view;

import base.FootballClub;
import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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


        /*
        Text t1 = new Text("a\nb\nc\nd");
        Text t2 = new Text("a\nb\nc\nd");
        Text t3 = new Text("a\nb\nc\nd");
        Text t4 = new Text("a\nb\nc\nd");
        Text t5 = new Text("a\nb\nc\nd");
        Text t6 = new Text("a\nb\nc\nd");
        Text t7 = new Text("a\nb\nc\nd");
        Text t8 = new Text("a\nb\nc\nd");
        Text t9 = new Text("a\nb\nc\nd");
        Text t10 = new Text("a\nb\nc\nd");
        Text t11 = new Text("a\nb\nc\nd");
        Text t12 = new Text("a\nb\nc\nd");
        Text t13 = new Text("a\nb\nc\nd");
        Text t14 = new Text("a\nb\nc\nd");
        Text t15 = new Text("a\nb\nc\nd");
        Text t16 = new Text("a\nb\nc\nd");


        groupsPane.add(t1,0,2);
        groupsPane.add(t2,0,3);
        groupsPane.add(t3,0,4);
        groupsPane.add(t4,0,5);
        groupsPane.add(t5,1,2);
        groupsPane.add(t6,1,3);
        groupsPane.add(t7,1,4);
        groupsPane.add(t8,1,5);
        groupsPane.add(t9,2,2);
        groupsPane.add(t10,2,3);
        groupsPane.add(t11,2,4);
        groupsPane.add(t12,2,5);
        groupsPane.add(t13,3,2);
        groupsPane.add(t14,3,3);
        groupsPane.add(t15,3,4);
        groupsPane.add(t16,3,5);
*/



        for (int i = 0; i < getAllGroups().size(); i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 4; l++) {
                    groupsPane.add(new Text(getAllGroups().get(i)), j, l);
                    System.out.println("elo");
                }
            }
        }
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

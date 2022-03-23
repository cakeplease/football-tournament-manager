package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
        groupsPane.add(t16,9,5);

    }

    //TODO hent arraylist
}

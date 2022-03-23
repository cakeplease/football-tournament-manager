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
        t.setText("This is a text sample");
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


        groupsPane.add(t1,0,2);
        groupsPane.add(t2,0,3);
        groupsPane.add(t3,0,4);
        groupsPane.add(t4,1,2);
        groupsPane.add(t5,1,3);
        groupsPane.add(t6,1,4);

    }

    //TODO hent arraylist
}

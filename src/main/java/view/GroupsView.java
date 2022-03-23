package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GroupsView {
    protected Pane groupsPane;
    private ScreenController screenController;

    public GroupsView(ScreenController ScreenController) {
        this.groupsPane = new Pane();
        this.screenController = ScreenController;
        this.setup();
    }

    public void setup() {
        //knapper
        //tekst
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setLayoutX(100);
        backButton.setLayoutY(500);
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        groupsPane.getChildren().add(backButton);

    }
}

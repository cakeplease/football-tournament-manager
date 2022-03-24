package view;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * Template for [name]View classes
 * Things that aren't commented stays the same
 */
public class ViewTemplate {
    protected GridPane pane;
    private ScreenController screenController;

    public ViewTemplate(ScreenController screenController) {
        this.pane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        pane.add(backButton, 0,1);

        Text sceneTitle = new Text("Scene title");
        sceneTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        pane.add(sceneTitle, 1, 2);

        //your code goes here...
    }
}

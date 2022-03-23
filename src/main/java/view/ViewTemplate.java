package view;

import javafx.scene.layout.GridPane;
import javafx.scene.control.*;
import javafx.scene.text.*;

/**
 * Template for [name]View classes
 * Things that aren't commented stays the same
 */
public class ViewTemplate {
    //Change variable name to [viewname]Pane
    protected GridPane viewNamePane;
    private ScreenController screenController;

    public ViewTemplate(ScreenController screenController) {
        this.viewNamePane = new GridPane();
        this.screenController = screenController;
        this.setup();
    }

    public void setup() {
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));
        viewNamePane.add(backButton, 0,1);

        //your code goes here...
    }
}

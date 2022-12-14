package view;

import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 * Shows all generated groups consists of 4 teams
 */
public class GroupsView extends View {
    protected GridPane pane;
    private ScreenController screenController;

    /**
     * GroupsView constructor
     * @param ScreenController ScreenController object
     */
    public GroupsView(ScreenController ScreenController) {
        this.pane = new GridPane();
        this.screenController = ScreenController;
        this.setup();
    }

    /**
     * Gets pane
     * @return pane
     */
    public Pane getPane() {
        return this.pane;
    }

    /**
     * Shows all generated groups
     */
    public void setup() {
        this.resetPane();

        GroupController groupController = GroupController.getInstance();

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        Text title = new Text();
        title.setText("Groups");
        title.setId("header-text");
        //title.setFont(Font.font ("Verdana", 30));
        pane.add(backButton, 0, 0);
        pane.add(title, 0, 1);
        pane.setHgap(150);
        pane.setVgap(10);
        pane.setPadding(new Insets(25,25,25,25));

        int i = 0;
        for (int columns = 0; columns < 4; columns++) {
            for (int rows = 0; rows < groupController.getGroups().size() / 4; rows++) {
                Text text = new Text(getAllGroups().get(i));
                //text.setFont(Font.font("Verdana", 20));
                text.setId("normal-text");
                pane.add(text, columns, rows + 5);
                i++;
            }
        }
    }

    /**
     * Takes the list of the groups and puts them in a list of strings
     * @return Arraylist of groups as readable format
     */
    public ArrayList<String> getAllGroups(){
        GroupController groupController = GroupController.getInstance();
        ArrayList<Group> groups = groupController.getGroups();
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

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

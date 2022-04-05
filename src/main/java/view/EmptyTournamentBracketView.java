package view;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import model.DataStorage;

public class EmptyTournamentBracketView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    public EmptyTournamentBracketView(ScreenController ScreenController) {
        this.pane = new GridPane();
        this.screenController = ScreenController;
        this.setup();
    }

    public Pane getPane() {
        return this.pane;
    }

    public void setup() {
        this.resetPane();

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        /*Button generateGroups = new Button();
        generateGroups.setText("Generate groups");
        generateGroups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DataStorage.load(true);
                //GUIController.generateGroups();
                screenController.activate("TournamentBracketView");

            }
        });
        pane.add(generateGroups, 1,0);*/

        /*Text title = new Text();
        title.setText("Tournament Bracket");
        title.setFont(Font.font ("Verdana", 30));
        tournamentBracketPane.add(title, 0, 1);*/
        pane.add(backButton, 0, 0);
        pane.setHgap(30);
        pane.setVgap(8);
        pane.setPadding(new Insets(25,25,25,25));

        PhongMaterial grey = new PhongMaterial();
        grey.setDiffuseColor(Color.DARKGREY);
        grey.setSpecularColor(Color.BLACK);


        /**
         * Method to print out boxes to the tournament bracket. The for loop goes through every number in the
         * columnNumbers array and uses them as column numbers. Then it makes the boxes according to the
         * column number.
         */

        int[] columnNumbers = new int[]{ 0,8,1,7,2,6,3,5,4};

        for(int i = 0; columnNumbers.length > i; i++){
            int column = columnNumbers[i];
            if(column == 0 || column == 8){
                for(int row = 2; row < 17; row++){ //(int row = 2; row < 10; row++)
                    Box box = new Box(125.0, 50.0,0.0);
                    box.setMaterial(grey);
                    pane.add(box, column, row);
                    row++;
                }
            }else if (column == 1 || column == 7){
                for(int row = 3; row < 16; row++){
                    Box box = new Box(125.0, 50.0,0.0);
                    box.setMaterial(grey);
                    pane.add(box, column, row);
                    row+=3;
                }
            } else if(column == 2 || column == 6){
                for(int row = 5; row < 14; row++){
                    Box box = new Box(125.0, 50.0,0.0);
                    box.setMaterial(grey);
                    pane.add(box, column, row);
                    row+=7;
                }
            } else{
                int row = 9;
                Box box = new Box(125.0, 50.0,0.0);
                box.setMaterial(grey);
                pane.add(box, column, row);
            }
        }
    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

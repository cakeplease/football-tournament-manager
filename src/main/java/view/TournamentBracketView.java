package view;

import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TournamentBracketView extends View{
    protected GridPane tournamentBracketPane;
    private ScreenController screenController;

    public TournamentBracketView(ScreenController ScreenController) {
        this.tournamentBracketPane = new GridPane();
        this.screenController = ScreenController;
        this.setup();
    }

    @Override
    Pane getPane() {
        return this.tournamentBracketPane;
    }

    public void setup() {
        /*Text text = new Text("test");
        Region rect = new Region();
        rect.setStyle("-fx-background-color: red; -fx-border-style: solid; -fx-border-width: 5; -fx-border-color: black; -fx-min-width: 20; -fx-min-height:50; -fx-max-width:50; -fx-max-height: 50;");
        this.tournamentBracketPane.getChildren().addAll(rect, text);*/

        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(e -> screenController.activate("FrontPage"));

        /*Text title = new Text();
        title.setText("Tournament Bracket");
        title.setFont(Font.font ("Verdana", 30));
        tournamentBracketPane.add(title, 0, 1);*/
        tournamentBracketPane.add(backButton, 0, 0);
        tournamentBracketPane.setHgap(50);
        tournamentBracketPane.setVgap(8);
        tournamentBracketPane.setPadding(new Insets(25,25,25,25));


        PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);
        /*PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);

        for(int column = 0; column < 7; column++){
            for(int row = 2; row < 17; row++){ //(int row = 2; row < 10; row++)
                Box box = new Box(100.0, 50.0,0.0);
                box.setMaterial(redMaterial);
                tournamentBracketPane.add(box, column, row);
                row++;
            }
            column += 5;
        }

        for(int column = 1; column < 6; column++){
            for(int row = 3; row < 16; row++){
                Box box = new Box(100.0, 50.0,0.0);
                box.setMaterial(redMaterial);
                tournamentBracketPane.add(box, column, row);
                row+=3;
            }
            column += 3;
        }*/


        /**
         * Method to print out boxes to the tournament bracket. The for loop goes through every number in the
         * columnNumbers array and uses them as column numbers. Then it makes the boxes according to the
         * column number.
         */

        //ArrayList<Integer> columnNumbers = new ArrayList<>();
        int[] columnNumbers = new int[]{ 0,8,1,7,2,6,3,5,4};

        for(int i = 0; columnNumbers.length > i; i++){
            int column = columnNumbers[i];
            if(column == 0 || column == 8){
                for(int row = 2; row < 17; row++){ //(int row = 2; row < 10; row++)
                    Box box = new Box(100.0, 50.0,0.0);
                    box.setMaterial(redMaterial);
                    tournamentBracketPane.add(box, column, row);
                    //tournamentBracketPane.add(new Box(100.0, 50.0,0.0), column, row);
                    row++;
                }
            }else if (column == 1 || column == 7){
                for(int row = 3; row < 16; row++){
                    Box box = new Box(100.0, 50.0,0.0);
                    box.setMaterial(redMaterial);
                    tournamentBracketPane.add(box, column, row);
                    row+=3;
                }
            } else if(column == 2 || column == 6){
                for(int row = 5; row < 14; row++){
                    Box box = new Box(100.0, 50.0,0.0);
                    box.setMaterial(redMaterial);
                    tournamentBracketPane.add(box, column, row);
                    row+=7;
                }
            } else{
                int row = 9;
                Box box = new Box(100.0, 50.0,0.0);
                box.setMaterial(redMaterial);
                tournamentBracketPane.add(box, column, row);
            }
        }


        /*Box box = new Box(100.0, 50.0,0.0);
        box.setMaterial(redMaterial);
        tournamentBracketPane.add(box, 0, 2);*/




        //box.setTranslateX(100);
        //box.setTranslateY(100);


    }
}

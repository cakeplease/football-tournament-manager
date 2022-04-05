package view;

import base.Group;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class TournamentBracketView extends View{
    protected GridPane pane;
    private ScreenController screenController;

    public TournamentBracketView(ScreenController ScreenController) {
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

        /*Text title = new Text();
        title.setText("Tournament Bracket");
        title.setFont(Font.font ("Verdana", 30));
        pane.add(title, 0, 1);*/
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
                    //pane.add(new Box(100.0, 50.0,0.0), column, row);
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
        //DET VAR TIDLIGERE Hgap(60) OG HORISONTAL LENGDE PÅ BOKSEN 100.0

        //box.setTranslateX(100);
        //box.setTranslateY(100);

        /**
         * GROUPS
         */

        int teams = 0;
        if(getStartingTeams().size() == 32){
            for(int column = 0; 9 > column; column++){
                for(int row = 2; row < 17; row++){
                    Text text = new Text(getStartingTeams().get(teams));
                    System.out.println(text);
                    text.setFont(Font.font ("Verdana", 11));
                    text.setFill(Color.DARKGREEN);
                    pane.add(text, column, row);
                    row++;
                    teams++;
                }
                column += 7;
            }
        }
/*
        int teams = 0;
        for(int column = 0; 9 > column; column++){
            for(int row = 2; row < 17; row++){
                Text text = new Text(getStartingTeams().get(teams));
                System.out.println(text);
                text.setFont(Font.font ("Verdana", 11));
                text.setFill(Color.DARKGREEN);
                pane.add(text, column, row);
                row++;
                teams++;
            }
            column += 7;
        }*/


    }

    public ArrayList<String> getStartingTeams(){
        //DataStorage.load(true);//TODO dette er bare en test
        GroupController groupController = GroupController.getInstance();

        ArrayList<Group> groups = groupController.getGroups();
        System.out.println(groups);
        ArrayList<String> strings = new ArrayList<>();


        for (Group group : groups) {
            strings.add(group.getGroupTeams().get(0).getName() + "\n" + group.getGroupTeams().get(1).getName());
            strings.add(group.getGroupTeams().get(2).getName() + "\n" + group.getGroupTeams().get(3).getName());
        }
        System.out.println(strings);
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

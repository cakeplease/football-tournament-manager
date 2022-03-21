package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FrontPageScene {
    private Group root = new Group();
    protected Scene frontPageScene = new Scene(root);
    private Text welcome = new Text("Scandia Cup 2022");
    private Button addTeam, showAllTeams, showMatches, showGroups, showTournamentBracket;
    private Image image;

    public FrontPageScene(){
        addTeam = new Button();
        addTeam.setText("Add team");
        addTeam.setLayoutX(100);
        addTeam.setLayoutY(500);
        //TODO: add event(scene switch) and styling

        showAllTeams = new Button();
        showAllTeams.setText("Show all teams");
        showAllTeams.setLayoutX(200);
        showAllTeams.setLayoutY(500);
        //TODO: add event(scene switch) and styling

        showMatches = new Button();
        showMatches.setText("Show matches");
        showMatches.setLayoutX(300);
        showMatches.setLayoutY(500);
        //TODO: add event(scene switch) and styling

        showGroups = new Button();
        showGroups.setText("Show groups");
        showGroups.setLayoutX(400);
        showGroups.setLayoutY(500);
        //TODO: add event(scene switch) and styling

        showTournamentBracket = new Button();
        showTournamentBracket.setText("Show tournament bracket");
        showTournamentBracket.setLayoutX(250);
        showTournamentBracket.setLayoutY(700);
        //TODO: add event(scene switch) and styling

        welcome.setLayoutX(400);
        welcome.setLayoutY(400);
        welcome.setFont(new Font("Verdana", 40));
        //TODO: add styling

        image = new Image("frontPage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);

        root.getChildren().add(imageView);
        root.getChildren().add(addTeam);
        root.getChildren().add(showAllTeams);
        root.getChildren().add(showMatches);
        root.getChildren().add(showGroups);
        root.getChildren().add(showTournamentBracket);
        root.getChildren().add(welcome);






    }



}

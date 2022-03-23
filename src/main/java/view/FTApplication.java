package view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FTApplication extends Application {
    private Pane FrontPage = new Pane();
    private Scene frontPageScene = new Scene(FrontPage);
    protected ScreenController screenController = new ScreenController(frontPageScene);

    private AddTeamView AddTeamView = new AddTeamView(screenController);
    private GroupsView GroupsView = new GroupsView(screenController);
    private TournamentBracketView TournamentBracketView = new TournamentBracketView(screenController);


    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        screenController.addScreen("Groups", GroupsView.groupsPane);
        screenController.addScreen("AddTeam", AddTeamView.addTeamPane);
        screenController.addScreen("TournamentBracket", TournamentBracketView.tournamentBracketPane);


        Image icon = new Image ("logo1.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setTitle("Scandia Cup Manager");
        frontPageScene.getStylesheets().add("/styles.css");

        Button addTeam = new Button();
        addTeam.setText("Add team");
        addTeam.setLayoutX(100);
        addTeam.setLayoutY(500);
        addTeam.setOnAction(e -> screenController.activate("AddTeam"));

        Button showAllTeams = new Button();
        showAllTeams.getStyleClass().add("button");
        showAllTeams.setText("Show all teams");
        showAllTeams.setLayoutX(200);
        showAllTeams.setLayoutY(500);
        showAllTeams.setOnAction(e -> screenController.activate("Teams"));

        Button showMatches = new Button();
        showMatches.setText("Show matches");
        showMatches.setLayoutX(300);
        showMatches.setLayoutY(500);
        showMatches.setOnAction(e -> screenController.activate("Matches"));

        Button showGroups = new Button();
        showGroups.setText("Show groups");
        showGroups.setLayoutX(400);
        showGroups.setLayoutY(500);
        showGroups.setOnAction(e -> screenController.activate("Groups"));

        Button showTournamentBracket = new Button();
        showTournamentBracket.setText("Show tournament bracket");
        showTournamentBracket.setLayoutX(250);
        showTournamentBracket.setLayoutY(700);
        showTournamentBracket.setOnAction(e -> screenController.activate("TournamentBracket"));

        Text welcome = new Text("Scandia Cup 2022");
        welcome.setLayoutX(400);
        welcome.setLayoutY(400);
        welcome.setFont(new Font("Verdana", 40));

        Image image = new Image("/frontPage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);

        FrontPage.getChildren().add(imageView);
        FrontPage.getChildren().add(addTeam);
        FrontPage.getChildren().add(showAllTeams);
        FrontPage.getChildren().add(showMatches);
        FrontPage.getChildren().add(showGroups);
        FrontPage.getChildren().add(showTournamentBracket);
        FrontPage.getChildren().add(welcome);

        primaryStage.setScene(frontPageScene);
        primaryStage.show();
    }

    /**
     * Uses the static launch() method to launch the stage.
     * @param args
     */
    public static void main(String[] args) {
        FTApplication.launch(args);
    }

}

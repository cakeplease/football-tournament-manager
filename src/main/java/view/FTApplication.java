package view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FTApplication extends Application {
    private StackPane frontPage = new StackPane();
    private Scene frontPageScene = new Scene(frontPage);
    protected ScreenController screenController = new ScreenController(frontPageScene);

    private AddTeamView addTeamView = new AddTeamView(screenController);
    private GroupsView groupsView = new GroupsView(screenController);
    private TournamentBracketView tournamentBracketView = new TournamentBracketView(screenController);


    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        screenController.addScreen("FrontPage", frontPage);
        screenController.addScreen("Groups", groupsView.groupsPane);
        screenController.addScreen("AddTeam", addTeamView.addTeamPane);
        screenController.addScreen("TournamentBracket", tournamentBracketView.tournamentBracketPane);

        Image icon = new Image ("logo1.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setFullScreen(true);

        primaryStage.setTitle("Scandia Cup Manager");
        frontPageScene.getStylesheets().add("/styles.css");
        frontPage.getStyleClass().add("front-page");


        this.setup();

        primaryStage.setScene(frontPageScene);
        primaryStage.show();
    }
    
    private void setup() {



        Button addTeam = new Button();
        addTeam.setText("Add team");
        addTeam.setAlignment(Pos.BOTTOM_LEFT);
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
        welcome.setFont(new Font("Verdana", 40));

        HBox buttons = new HBox();
        buttons.getChildren().addAll(addTeam, showAllTeams, showMatches, showGroups, showTournamentBracket);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        Image image = new Image("/frontPage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);
        imageView.getStyleClass().add("image");
        VBox vBoxImg = new VBox();
        vBoxImg.getChildren().addAll(imageView, welcome, buttons);
        vBoxImg.setAlignment(Pos.TOP_CENTER);
        vBoxImg.setSpacing(20);


        frontPage.getChildren().add(vBoxImg);

    }

    /**
     * Uses the static launch() method to launch the stage.
     * @param args
     */
    public static void main(String[] args) {
        FTApplication.launch(args);
    }

}

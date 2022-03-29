package view;
import controller.GroupController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    GroupController groupController = GroupController.getInstance();
    private StackPane frontPage = new StackPane();
    private Scene frontPageScene = new Scene(frontPage);
    protected ScreenController screenController = new ScreenController(frontPageScene);

    private FrontpageView frontpage = new FrontpageView(screenController);
    private AddTeamView addTeamView = new AddTeamView(screenController);
    private GroupsView groupsView = new GroupsView(screenController);
    private NoGroupsView noGroupsView = new NoGroupsView(screenController);
    private TeamsView teamsView = new TeamsView(screenController);
    private MatchesView matchesView = new MatchesView(screenController);
    private TournamentBracketView tournamentBracketView = new TournamentBracketView(screenController);
    private EmptyTournamentBracketView emptyTournamentBracketView = new EmptyTournamentBracketView(screenController);

    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        screenController.addScreen("FrontPage", frontpage);
        screenController.addScreen("Groups", groupsView);
        screenController.addScreen("NoGroups", noGroupsView);
        screenController.addScreen("AddTeam", addTeamView);
        screenController.addScreen("TournamentBracket", tournamentBracketView);
        screenController.addScreen("Teams", teamsView);
        screenController.addScreen("Matches", matchesView);
        screenController.addScreen("EmptyTournamentBracketView", emptyTournamentBracketView);

        Image icon = new Image ("logo1.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setFullScreen(true);

        primaryStage.setTitle("Scandia Cup Manager");
        frontPageScene.getStylesheets().add("/styles.css");
        frontPage.getStyleClass().add("front-page");
        primaryStage.setScene(frontPageScene);
        primaryStage.show();

        this.setup();

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

        showGroups.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (groupController.getGroups().isEmpty()) {
                    screenController.activate("NoGroups");
                } else {
                    screenController.activate("Groups");
                }
            }
        });

        Button showTournamentBracket = new Button();
        showTournamentBracket.setText("Show tournament bracket");
        showTournamentBracket.setLayoutX(250);
        showTournamentBracket.setLayoutY(700);
        /*showTournamentBracket.setOnAction(e -> screenController.activate("TournamentBracket"));*/
        showTournamentBracket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(groupController.getGroups().isEmpty()){
                    screenController.activate("EmptyTournamentBracketView");
                } else{
                    screenController.activate("TournamentBracketView");
                }
            }
        });


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

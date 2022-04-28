package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FTApplication main class that extends Application and runs program
 * It shows Start and Quit button on the frontpage and prepares all screens
 */
public class FTApplication extends Application {
    private BorderPane frontPage = new BorderPane();
    private Scene frontPageScene = new Scene(frontPage);
    protected ScreenController screenController = new ScreenController(frontPageScene);

    private FrontpageView frontpage = new FrontpageView(screenController);
    private AddTeamView addTeamView = new AddTeamView(screenController);
    private GroupsView groupsView = new GroupsView(screenController);
    private NoGroupsView noGroupsView = new NoGroupsView(screenController);
    private TeamsView teamsView = new TeamsView(screenController);
    private MatchesView matchesView = new MatchesView(screenController);
    private TournamentBracketView tournamentBracketView = new TournamentBracketView(screenController);

    /**
     * window size x dir
     */
    public static double windowSizeX;

    /**
     * window size y dir
     */
    public static double windowSizeY;

    /**
     * The main stage
     */
    public static Stage primaryStage;

    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     *
     * @param primaryStage Stage to show
     */
    @Override
    public void start(Stage primaryStage) {
        FTApplication.primaryStage = primaryStage;

        screenController.addScreen("FrontPage", frontpage);
        screenController.addScreen("Groups", groupsView);
        screenController.addScreen("NoGroups", noGroupsView);
        screenController.addScreen("AddTeam", addTeamView);
        screenController.addScreen("Teams", teamsView);
        screenController.addScreen("Matches", matchesView);
        screenController.addScreen("TournamentBracketView", tournamentBracketView);

        Image icon = new Image("logo.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setFullScreen(true);

        primaryStage.setTitle("Skandia Cup Manager");
        frontPageScene.getStylesheets().add("/styles.css");
        frontPage.getStyleClass().add("#front-page");

        primaryStage.setScene(frontPageScene);
        primaryStage.show();

        windowSizeX = primaryStage.getX();
        windowSizeY = primaryStage.getY();

        this.setup();

    }

    private void setup() {

        Text welcome = new Text("SKANDIA CUP 2022");
        welcome.setId("title-text");

        Button start = new Button();
        start.setText("Start");
        start.setStyle("-fx-font-size:20");
        start.setOnAction(e -> screenController.activate("FrontPage"));

        Button exit = new Button();
        exit.setText("Quit");
        exit.setStyle("-fx-font-size:20");
        exit.setOnAction(e -> System.exit(0));

        Button changeMode = new Button();
        changeMode.setText("Change theme");
        this.frontPage.setTop(changeMode);
        changeMode.setOnAction(e -> screenController.changeMode());

        //Image image = new Image("/frontPage.jpg");
        Image image = new Image("/logo.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);
        imageView.getStyleClass().add("image");
        VBox vBoxImg = new VBox();

        vBoxImg.getChildren().addAll(imageView, welcome, start, exit);
        vBoxImg.setAlignment(Pos.TOP_CENTER);
        vBoxImg.setSpacing(20);
        vBoxImg.setPadding(new Insets(50, 50, 50, 50));

        frontPage.setPadding(new Insets(10, 10, 10, 10));
        frontPage.setCenter(vBoxImg);
    }

    /**
     * Uses the static launch() method to launch the stage.
     *
     * @param args args
     */
    public static void main(String[] args) {
        FTApplication.launch(args);
    }

}

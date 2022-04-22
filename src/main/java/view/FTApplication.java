package view;
import controller.GUIController;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

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
    private NewTournamentBracketView newTournamentBracketView = new NewTournamentBracketView(screenController);

    private static double windowSizeX;
    private static double windowSizeY;

    public static Stage primaryStage;

    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        FTApplication.primaryStage = primaryStage;

        screenController.addScreen("FrontPage", frontpage);
        screenController.addScreen("Groups", groupsView);
        screenController.addScreen("NoGroups", noGroupsView);
        screenController.addScreen("AddTeam", addTeamView);
        screenController.addScreen("TournamentBracket", tournamentBracketView);
        screenController.addScreen("Teams", teamsView);
        screenController.addScreen("Matches", matchesView);
        screenController.addScreen("NewTournamentBracketView", newTournamentBracketView);

        Image icon = new Image ("logo1.png");
        primaryStage.getIcons().add(icon);

        primaryStage.setFullScreen(true);

        primaryStage.setTitle("Skandia Cup Manager");
        frontPageScene.getStylesheets().add("/styles.css");
        frontPage.getStyleClass().add("#front-page");
        frontPageScene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setScene(frontPageScene);
        primaryStage.show();

        windowSizeX = primaryStage.getX();
        windowSizeY = primaryStage.getY();

        this.setup();

    }
    
    private void setup() {

        Text welcome = new Text("SKANDIA CUP 2022");
        welcome.setFont(new Font("Verdana", 40));

        Button start = new Button();
        start.setText("Start");
        start.setStyle("-fx-font-size:28");
        start.setOnAction(e -> screenController.activate("FrontPage"));

        Button exit = new Button();
        exit.setText("Quit");
        exit.setStyle("-fx-font-size:20");
        exit.setOnAction(e -> System.exit(0));

        Image image = new Image("/frontPage.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);
        imageView.getStyleClass().add("image");
        VBox vBoxImg = new VBox();

        vBoxImg.getChildren().addAll(imageView, welcome, start, exit);
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

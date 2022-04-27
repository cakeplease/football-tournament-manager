package view;

import controller.GUIController;
import controller.GroupController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * FrontpageView shows frontpage
 */
public class FrontpageView extends View {
    protected BorderPane myPane;
    protected StackPane pane;
    private ScreenController screenController;

    /**
     * FrontpageView constructor
     * @param ScreenController
     */
    public FrontpageView(ScreenController ScreenController) {
        this.pane = new StackPane();
        this.screenController = ScreenController;
        this.myPane = new BorderPane();

        this.setup();
    }

    /**
     * Gets pane
     * @return
     */
    public Pane getPane() {
        return this.myPane;
    }

    /**
     * Sets up title and menu buttons
     */
    public void setup() {
        this.resetPane();

        Button changeMode = new Button();
        changeMode.setText("Change theme");
        this.myPane.setTop(changeMode);
        changeMode.setOnAction(e -> screenController.changeMode());

        GroupController groupController = GroupController.getInstance();

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
        showGroups.setOnAction(e -> {
            if (groupController.getGroups().isEmpty()) {
                screenController.activate("NoGroups");
            } else {
                screenController.activate("Groups");
            }
        });

        Button showTournamentBracket = new Button();
        showTournamentBracket.setText("Show tournament bracket");
        showTournamentBracket.setLayoutX(250);
        showTournamentBracket.setLayoutY(700);
        showTournamentBracket.setOnAction(e -> screenController.activate("TournamentBracketView"));

        Button loadTestData = new Button();
        loadTestData.setText("LOAD TEST DATA");
        loadTestData.setOnAction(e -> {
            GUIController.loadTestData();
            loadTestData.setDisable(true);
        });

        Button exit = new Button();
        exit.setText("Quit");
        exit.setAlignment(Pos.BOTTOM_LEFT);
        exit.setOnAction(e -> System.exit(0));

        Text welcome = new Text("SKANDIA CUP 2022");
        welcome.setId("title-text");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(addTeam, showAllTeams, showMatches, showGroups, showTournamentBracket, loadTestData, exit);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        Image image = new Image("/logo.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(400);
        imageView.setY(50);
        imageView.getStyleClass().add("image");
        VBox vBoxImg = new VBox();
        vBoxImg.getChildren().addAll(imageView, welcome, buttons);
        vBoxImg.setAlignment(Pos.TOP_CENTER);
        vBoxImg.setSpacing(20);
        vBoxImg.setPadding(new Insets(50, 50, 50, 50));

        myPane.setPadding(new Insets(10, 10, 10, 10));
        myPane.setCenter(vBoxImg);

    }

    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

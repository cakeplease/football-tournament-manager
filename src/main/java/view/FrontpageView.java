package view;

import controller.GUIController;
import controller.GroupController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FrontpageView extends View {
    protected StackPane pane;
    private ScreenController screenController;

    public FrontpageView(ScreenController ScreenController) {
        this.pane = new StackPane();
        this.screenController = ScreenController;

        this.setup();
    }

    public Pane getPane() {
        return this.pane;
    }

    public void setup() {
        GroupController groupController = GroupController.getInstance();

        Button addTeam = new Button();
        addTeam.setText("Add team");
        addTeam.setAlignment(Pos.BOTTOM_LEFT);
        addTeam.getStyleClass().setAll("btn","btn-primary");
        addTeam.setOnAction(e -> screenController.activate("AddTeam"));

        Button showAllTeams = new Button();
        showAllTeams.getStyleClass().add("button");
        showAllTeams.setText("Show all teams");
        showAllTeams.setLayoutX(200);
        showAllTeams.setLayoutY(500);
        showAllTeams.getStyleClass().setAll("btn","btn-primary");
        showAllTeams.setOnAction(e -> screenController.activate("Teams"));

        Button showMatches = new Button();
        showMatches.setText("Show matches");
        showMatches.setLayoutX(300);
        showMatches.setLayoutY(500);
        showMatches.getStyleClass().setAll("btn","btn-primary");
        showMatches.setOnAction(e -> screenController.activate("Matches"));

        Button showGroups = new Button();
        showGroups.setText("Show groups");
        showGroups.setLayoutX(400);
        showGroups.setLayoutY(500);
        showGroups.getStyleClass().setAll("btn","btn-primary");
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
        showTournamentBracket.getStyleClass().setAll("btn","btn-primary");
        showTournamentBracket.setOnAction(e -> screenController.activate("NewTournamentBracketView"));

        Button loadTestData = new Button();
        loadTestData.setText("LOAD TEST DATA");
        loadTestData.getStyleClass().setAll("btn","btn-primary");
        loadTestData.setOnAction(e -> GUIController.loadTestData());

        Button exit = new Button();
        exit.setText("Quit");
        exit.setAlignment(Pos.BOTTOM_LEFT);
        exit.getStyleClass().setAll("btn","btn-primary");
        exit.setOnAction(e -> System.exit(0));

        Text welcome = new Text("SKANDIA CUP 2022");
        welcome.setFont(new Font("Verdana", 40));

        HBox buttons = new HBox();
        buttons.getChildren().addAll(addTeam, showAllTeams, showMatches, showGroups, showTournamentBracket, loadTestData, exit);
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
        pane.getChildren().add(vBoxImg);
    }
    /**
     * Resets pane
     * (Removes all children from the list)
     */
    protected void resetPane() {
        this.pane.getChildren().clear();
    }
}

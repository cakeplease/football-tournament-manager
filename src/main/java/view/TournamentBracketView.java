package view;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TournamentBracketView {
    protected StackPane tournamentBracketPane;
    private ScreenController screenController;

    public TournamentBracketView(ScreenController ScreenController) {
        this.tournamentBracketPane = new StackPane();
        this.screenController = ScreenController;
        this.setup();
    }

    public void setup() {
        Text text = new Text("test");
        Region rect = new Region();
        rect.setStyle("-fx-background-color: red; -fx-border-style: solid; -fx-border-width: 5; -fx-border-color: black; -fx-min-width: 20; -fx-min-height:50; -fx-max-width:50; -fx-max-height: 50;");
        this.tournamentBracketPane.getChildren().addAll(rect, text);
    }
}

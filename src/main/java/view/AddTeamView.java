package view;

import javafx.scene.layout.Pane;

public class AddTeamView {
    protected Pane addTeamPane;
    private ScreenController screenController;

    public AddTeamView(ScreenController ScreenController) {
        this.addTeamPane = new Pane();
        this.screenController = ScreenController;
        this.setup();
    }

    public void setup() {

    }
}

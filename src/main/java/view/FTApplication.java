package view;
import javafx.application.Application;
import javafx.stage.Stage;

public class FTApplication extends Application{

    /**
     * Overrides the start() method which takes a single parameter stage.
     * Uses the show() method to display the stage.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Scandia Cup Manager");

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

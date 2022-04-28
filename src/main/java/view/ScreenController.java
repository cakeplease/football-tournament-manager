package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import java.util.HashMap;

/**
 * ScreenController class
 * Has an overview over all panes and can be used to change current pane
 */
public class ScreenController {
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private HashMap<String, View> viewMap = new HashMap<>();
    private Scene main;
    private boolean isDarkMode = false;

    /**
     * ScreenController constructor
     * @param main main scene
     */
    public ScreenController(Scene main) {
        this.main = main;
    }

    /**
     * Adds new screen (View class)
     * @param name name of the screen
     * @param view view class
     */
    public void addScreen(String name, View view) {
        screenMap.put(name, view.getPane());
        viewMap.put(name, view);
    }

    /**
     * Activates chosen screen by running its setup function
     * @param name name of the screen
     */
    protected void activate(String name) {
        viewMap.get(name).setup();
        main.setRoot(screenMap.get(name));
    }

    /**
     * Changes mode according to the current mode
     */
    public void changeMode() {
        if (!this.isDarkMode) {
            main.getStylesheets().remove("styles.css");
            main.getStylesheets().add("dark-mode.css");
            this.isDarkMode = true;
        } else {
            main.getStylesheets().remove("dark-mode.css");
            main.getStylesheets().add("styles.css");
            this.isDarkMode = false;
        }
    }
}
package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {

    private HashMap<String, Pane> screenMap = new HashMap<>();
    private HashMap<String, View> viewMap = new HashMap<>();
    private Scene main;
    private View view;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, View view){
        screenMap.put(name, view.getPane());
        viewMap.put(name, view);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        viewMap.get(name).setup();
        main.setRoot( screenMap.get(name));
    }
}
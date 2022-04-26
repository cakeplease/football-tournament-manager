package view;

import javafx.scene.layout.Pane;

/**
 * View class used as template for other view classes
 */
abstract class View {
        abstract Pane getPane();
        abstract void setup();
        abstract void resetPane();
}

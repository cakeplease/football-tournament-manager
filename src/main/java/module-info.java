module footballtournamentapplication {
    requires javafx.controls;
    requires javafx.fxml;


    //opens footballtournamentapplication to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports base;
    opens base to javafx.fxml;
}
module no.ntnu.idatt1002.k0208.footballtournamentapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens no.ntnu.idatt1002.k0208.footballtournamentapplication to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
    exports view;
    opens view to javafx.fxml;
    exports controller;
    opens controller to javafx.fxml;
    exports base;
    opens base to javafx.fxml;
}
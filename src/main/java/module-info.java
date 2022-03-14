module no.ntnu.idatt1002.k0208.footballtournamentapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens no.ntnu.idatt1002.k0208.footballtournamentapplication to javafx.fxml;
    exports no.ntnu.idatt1002.k0208.footballtournamentapplication;
}
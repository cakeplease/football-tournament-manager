package no.ntnu.idatt1002.k0208.footballtournamentapplication;

import base.FootballClub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FootballClubTest {

    @Test
    @DisplayName("Check if data is saved")
    public void checkIfDataIsSaved() {
        FootballClub fc = new FootballClub("Test", "Norway");
        fc.saveFootballClub();
    }
}

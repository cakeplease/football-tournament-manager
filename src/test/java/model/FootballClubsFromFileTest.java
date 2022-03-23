package model;

import base.FootballClub;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FootballClubsFromFileTest {

    @Test
    void readFromFile() {
        assertNotEquals(FootballClubsFromFile.readFromFile(), null);
    }
}
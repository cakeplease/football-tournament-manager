package base;

import base.FootballClub;
import base.Match;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Nested
    class ExceptionsTest{
        @Test
        void nullPointerExceptionTest () {
            FootballClub footballClub1 = null;
            FootballClub footballClub2 = new FootballClub("Daniels lag", "Norway");
            assertThrows(NullPointerException.class, () -> {new Match(footballClub1, footballClub2);});
        }

        @Test
        void illegalArgumentException () {
            FootballClub footballClub1 = new FootballClub("Daniels lag", "Norway");
            FootballClub footballClub2 = new FootballClub("Nessis lag", "Ghana");
            int score1 = 10;
            int score2 = -1;
            assertThrows(IllegalArgumentException.class, () -> {new Match(footballClub1, footballClub2, score1, score2);});
        }
    }

    @Nested
    class SetVariablesTest {
        @Test
        void setWinnerTest() {
            FootballClub footballClub1 = new FootballClub("Daniel", "Norway");
            FootballClub footballClub2 = new FootballClub("Nessi", "Ghana");
            Match match = new Match(footballClub1, footballClub2);
            match.setWinner(footballClub1);
            assertEquals(footballClub1, match.getWinner());
        }
    }

    @Nested
    class GetWinnerTests {
        @Test
        void getWinnerTestFirstConstructor() {
            FootballClub footballClub1 = new FootballClub("Daniels lag", "Norway");
            FootballClub footballClub2 = new FootballClub("Nessis lag", "Ghana");
            Match match = new Match(footballClub1, footballClub2, 2, 1);
            Match match2 = new Match(footballClub1, footballClub2, 0, 1);
            Match match3 = new Match(footballClub1, footballClub2, 0, 0);
            assertEquals(footballClub1, match.getWinner());
            assertEquals(footballClub2, match.getFootballClub2());
            assertNull(match3.getWinner());
        }

        @Test
        void getWinnerTestSecondConstructor() {
            FootballClub footballClub1 = new FootballClub("Daniels lag", "Norway");
            FootballClub footballClub2 = new FootballClub("Daniels lag2", "Norway");
            Match match = new Match(footballClub1, footballClub2);
            match.setScore1(2);
            match.setScore2(0);
            assertEquals(footballClub1, match.getWinner());
        }
    }
}
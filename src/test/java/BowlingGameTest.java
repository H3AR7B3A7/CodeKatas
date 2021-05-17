import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    BowlingGame game;

    @BeforeEach
    void setup() {
        game = new BowlingGame();
    }

    @Test
    void canScoreGutterGame() {
        game.roll(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(0, game.score());
    }

    @Test
    void canScoreGameOfOnes() {
        game.roll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals(20, game.score());
    }

    @Test
    void canScoreSpareFollowedByThree() {
        game.roll(5, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(16, game.score());
    }

    @Test
    void canScoreStrikeFollowedByThreeThenThree() {
        game.roll(10, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        assertEquals(22, game.score());
    }

    @Test
    void canScorePerfectGame() {
        game.roll(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
        assertEquals(300, game.score());
    }

}
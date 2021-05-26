import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {

    GameOfLife game;

    @BeforeEach
    void setup(){
        game = new GameOfLife();
    }

    @Test
    void canAddCellToGame(){
        game.addCell(new Cell(0, 0));
    }

    @Test
    void aSingleCellShouldHaveZeroNeighbors(){
        game.addCell(new Cell(0, 0));

        assertEquals(0, game.getNeighbors(new Cell(0, 0)).size());
    }

    @Test
    void twoCellsNextToEachOtherShouldBeNeighbors() throws Exception {
        game.addCell(new Cell(0, 0));
        game.addCell(new Cell(0, 1));

        assertArrayEquals(new Cell[] { new Cell(0, 1)}, game.getNeighbors(new Cell(0, 0)).toArray());
        assertArrayEquals(new Cell[] { new Cell(0, 0)}, game.getNeighbors(new Cell(0, 1)).toArray());
    }

    @Test
    void twoCellsNotNextToEachOtherShouldNotBeNeighbors() throws Exception {
        game.addCell(new Cell(0, 0));
        game.addCell(new Cell(0, 2));

        assertEquals(0, game.getNeighbors(new Cell(0, 0)).size());
        assertEquals(0, game.getNeighbors(new Cell(0, 2)).size());
    }
}
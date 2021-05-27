package GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameOfLifeTest {
    private Board board;
    private GameOfLife game;

    @BeforeEach
    void setup() {
        board = new Board();
        game = new GameOfLife(board);
    }

    @Test
    void aSingleCell_shouldDie_inNextIteration() {
        board.addCell(new Cell(0, 0));
        Board nextBoard = game.nextIteration();

        assertFalse(nextBoard.isAlive(new Cell(0, 0)));
    }

    @Test
    void aCellWithTwoNeighbors_shouldLive_inNextIteration() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 1));
        board.addCell(new Cell(0, 2));
        Board nextBoard = game.nextIteration();

        assertTrue(nextBoard.isAlive(new Cell(0, 1)));
    }

    @Test
    void aCellWithThreeNeighbors_shouldLive_inNextIteration() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 1));
        board.addCell(new Cell(0, 2));
        board.addCell(new Cell(1, 1));
        Board nextBoard = game.nextIteration();

        assertTrue(nextBoard.isAlive(new Cell(0, 1)));
    }

    @Test
    void aCellWithMoreThanThreeNeighbors_shouldDie_inNextIteration() {
        addBlockOfCells(3, 3);
        Board nextBoard = game.nextIteration();

        assertFalse(nextBoard.isAlive(new Cell(1, 1)));
    }

    @Test
    void aDeadCellWithThreeNeighbors_shouldBeReborn_inNextIteration() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 1));
        board.addCell(new Cell(0, 2));
        Board nextBoard = game.nextIteration();

        assertTrue(nextBoard.isAlive(new Cell(1, 1)));
        assertTrue(nextBoard.isAlive(new Cell(-1, 1)));
    }

    @Test
    void shouldProcessMultipleIterations() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 2));
        board.addCell(new Cell(1, 1));

        game.run(4, 5);
    }

    @Test
    void acceptanceTest() {
        addBlockOfCells(3, 3);

        game.run(6, 10);
    }

    private void addBlockOfCells(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board.addCell(new Cell(i, j));
            }
        }
    }
}
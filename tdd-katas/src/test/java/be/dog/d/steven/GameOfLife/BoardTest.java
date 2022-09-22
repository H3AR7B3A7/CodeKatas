package be.dog.d.steven.GameOfLife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    void canAddCellToGame() {
        board.addCell(new Cell(0, 0));
    }

    @Test
    void aSingleCell_shouldHaveZeroNeighbors() {
        board.addCell(new Cell(0, 0));

        assertEquals(0, board.getNeighbors(new Cell(0, 0)).size());
    }

    @Test
    void twoCells_nextToEachOther_shouldBeNeighbors() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 1));

        assertArrayEquals(new Cell[]{new Cell(0, 1)}, board.getNeighbors(new Cell(0, 0)).toArray());
        assertArrayEquals(new Cell[]{new Cell(0, 0)}, board.getNeighbors(new Cell(0, 1)).toArray());
    }

    @Test
    void twoCells_notNextToEachOther_shouldNotBeNeighbors() {
        board.addCell(new Cell(0, 0));
        board.addCell(new Cell(0, 2));

        assertEquals(0, board.getNeighbors(new Cell(0, 0)).size());
        assertEquals(0, board.getNeighbors(new Cell(0, 2)).size());
    }

    @Test
    void middleCellOf_3x3block_shouldHaveEightNeighbors() {
        addBlockOfCells(3, 3);

        assertEquals(8, board.getNeighbors(new Cell(1, 1)).size());
    }

    private void addBlockOfCells(int x, int y) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board.addCell(new Cell(i, j));
            }
        }
    }
}
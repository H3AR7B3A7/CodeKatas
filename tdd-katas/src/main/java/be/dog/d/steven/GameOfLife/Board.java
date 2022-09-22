package be.dog.d.steven.GameOfLife;

import java.util.*;

public class Board {
    private final Set<Cell> cells = new HashSet<>();

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public Set<Cell> getNeighbors(Cell cell) {
        Set<Cell> neighbors = new HashSet<>();
        Set<Cell> neighborhood = getNeighborhood(cell);

        for (Cell c : neighborhood) {
            if (isAlive(c)) {
                neighbors.add(c);
            }
        }

        neighbors.remove(cell);

        return neighbors;
    }

    private Set<Cell> getNeighborhood(Cell cell) {
        Set<Cell> neighborhood = new HashSet<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                Cell c = new Cell(cell.x + dx, cell.y + dy);
                neighborhood.add(c);
            }
        }

        return neighborhood;
    }

    boolean isAlive(Cell c) {
        return cells.contains(c);
    }

    public Board nextIteration() {
        Board nextBoard = new Board();

        Set<Cell> deadCells = new HashSet<>();

        for (Cell c : cells) {
            if (hasHealthyAmountOfNeighbors(c)) {
                nextBoard.addCell(c);
            }
            deadCells.addAll(getNeighborhood(c));
        }

        for (Cell c : deadCells) {
            if (canBeReborn(c)) {
                nextBoard.addCell(c);
            }
        }

        return nextBoard;
    }

    private boolean canBeReborn(Cell c) {
        return !isAlive(c) && getNeighbors(c).size() == 3;
    }

    private boolean hasHealthyAmountOfNeighbors(Cell c) {
        return getNeighbors(c).size() == 2 || getNeighbors(c).size() == 3;
    }

    public boolean cellsOnBoard() {
        return cells.size() > 0;
    }
}
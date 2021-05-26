import java.util.*;

public class GameOfLife {

    private final Set<Cell> cells = new HashSet<>();

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        for (int dy = -1; dy <= 1; dy++) {
            Cell c = new Cell(cell.x, cell.y + dy);
            if (isAlive(c) && !c.equals(cell)){
                neighbors.add(c);
            }
        }

        return neighbors;
    }

    private boolean isAlive(Cell c) {
        return cells.contains(c);
    }
}

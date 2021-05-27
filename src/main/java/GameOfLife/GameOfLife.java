package GameOfLife;

public class GameOfLife {
    private Board board;

    public GameOfLife(Board board) {
        this.board = board;
    }

    public Board nextIteration() {
        board = board.nextIteration();
        return board;
    }

    public void print(int size) {
        for (int i = -size; i < size; i++) {
            for (int j = -size; j < size; j++) {
                System.out.print(board.isAlive(new Cell(j, i)) ? 'X' : ' ');
            }
            System.out.println();
        }
    }

    void run(int size, int time) {
        int countDown = time;
        while (board.cellsOnBoard() && countDown > 0) {
            print(size);
            board = board.nextIteration();
            countDown--;
        }
    }
}

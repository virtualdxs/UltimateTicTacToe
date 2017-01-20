@SuppressWarnings("serial")
public class TargetBoardFullException extends Exception {
    private int board;
    public TargetBoardFullException(int board) {
        super("Target board "+board+" is full.");
        this.board = board;
    }
    public int getBoard() {
        return board;
    }
}

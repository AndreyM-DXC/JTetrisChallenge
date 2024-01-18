package game;

public class StateSnapshot {
    /** Current board state, indexing starts from the top left corner */
    public final boolean[] board = new boolean[GameState.Width * GameState.Height];
    /** Current Piece to place */
    public GamePiece piece;
    /** Current Score */
    public int score;

    public boolean getCell(int x, int y) {
        return board[x + y * GameState.Width];
    }

    public StateSnapshot clone() {
        var result = new StateSnapshot();
        result.piece = piece;
        result.score = score;
        System.arraycopy(board, 0, result.board, 0, board.length);
        return result;
    }
}

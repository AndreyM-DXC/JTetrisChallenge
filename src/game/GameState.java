package game;

import java.util.Arrays;
import java.util.Random;

public class GameState {
    public static final int Width = 10;
    public static final int Height = 20;

    private final boolean[] board = new boolean[Width * Height];
    private final GamePiece[] pieces;
    private final Random random;

    private GamePiece piece;
    private int score;

    public GameState(int seed) {
        pieces = GamePiece.All.entrySet().stream().filter(p -> p.getKey().endsWith("-0")).map(p -> p.getValue()).toArray(GamePiece[]::new);
        random = new Random(seed);
        piece = pieces[random.nextInt(pieces.length)];
    }

    public int getScore() {
        return score;
    }

    public void copyTo(StateSnapshot snapshot) {
        snapshot.score = score;
        snapshot.piece = piece;
        System.arraycopy(board, 0, snapshot.board, 0, board.length);
    }

    public void apply(Command command) throws GameOverException {
        var layout = piece.rotate(command.rotation).getLayout(command.offset);

        if (!moveToNextLine(layout)) {
            throw new GameOverException();
        }
        while (moveToNextLine(layout)) {
        }
        for (var index : layout) {
            board[index - Width] = true;
        }
        var score = 1;
        for (var i = board.length - Width; i >= 0; i -= Width) {
            if (isFilled(i)) {
                this.score += score++;
                System.arraycopy(board, 0, board, Width, i);
                Arrays.fill(board, 0, Width, false);
                i += Width;
            }
        }
        this.score++;
        this.piece = pieces[random.nextInt(pieces.length)];
    }

    private boolean isFilled(int offset) {
        for (var i = 0; i < Width; i++) {
            if (!board[i + offset]) {
                return false;
            }
        }
        return true;
    }

    private boolean moveToNextLine(int[] layout) {
        for (var index : layout) {
            if (index >= board.length || board[index]) {
                return false;
            }
        }
        for (var i = 0; i < layout.length; i++) {
            layout[i] += Width;
        }
        return true;
    }
}

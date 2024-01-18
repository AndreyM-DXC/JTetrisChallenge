package game;

import java.util.HashMap;
import java.util.Map;

// O-0     I-0     I-1
// [][]    []      [][][][]
// [][]    []
//         []
//         []

// T-0     T-1     T-2     T-3
// [][][]  []        []      []
//   []    [][]    [][][]  [][]
//         []                []

// S-0     S-1     Z-0     Z-1
//   [][]  []      [][]      []
// [][]    [][]      [][]  [][]
//           []            []

// L-0     L-1     L-2     L-3
// []          []  [][]    [][][]
// []      [][][]    []    []
// [][]              []

// J-0     J-1     J-2     J-3
//   []    [][][]  [][]    []
//   []        []  []      [][][]
// [][]            []

public class GamePiece {
    /** Collection of all possible Pieces */
    public static final Map<String, GamePiece> All = init();

    /** Type of Piece: O, I, T, S, Z, L or J */
    public final char type;
    /** Widht of Piece */
    public final int width;
    /** Index of rotation from 0 to rotations-value exclusive */
    public final int rotation;
    /** Count of distinct rotations */
    public final int rotations;

    private final int[] layout;

    private GamePiece(char type, int rotation, int rotations, int width, int[] layout)
    {
        this.type = type;
        this.rotation = rotation;
        this.rotations = rotations;
        this.width = width;
        this.layout = layout;
    }

    public GamePiece rotate() {
        return All.get(type + "-" + (rotation + 1) % rotations);
    }

    public GamePiece rotate(int count) {
        var result = this;
        while (count-- > 0) {
            result = result.rotate();
        }
        return result;
    }

    @Override
    public String toString() {
        return type + "-" + rotation;
    }

    public int[] getLayout(int offset) {
        offset = Math.max(0, Math.min(offset, GameState.Width - width));

        var result = new int[layout.length];
        for (var i = 0; i < result.length; i++) {
            result[i] = layout[i] + offset;
        }
        return result;
    }

    private static Map<String, GamePiece> init() {
        final int W = GameState.Width;
        final Map<String, GamePiece> result = new HashMap<>();
        result.put("O-0", new GamePiece('O', 0, 1, 2, new int[] { W, W + 1, 0, 1 }));
        result.put("I-0", new GamePiece('I', 0, 2, 1, new int[] { W + W + W, W + W, W, 0 }));
        result.put("I-1", new GamePiece('I', 1, 2, 4, new int[] { 0, 1, 2, 3 }));
        result.put("T-0", new GamePiece('T', 0, 4, 3, new int[] { W + 1, 0, 1, 2 }));
        result.put("T-1", new GamePiece('T', 1, 4, 2, new int[] { W + W, W, W + 1, 0 }));
        result.put("T-2", new GamePiece('T', 2, 4, 3, new int[] { W, W + 1, W + 2, 1 }));
        result.put("T-3", new GamePiece('T', 3, 4, 2, new int[] { W + W + 1, W, W + 1, 1 }));
        result.put("S-0", new GamePiece('S', 0, 2, 3, new int[] { W, W + 1, 1, 2 }));
        result.put("S-1", new GamePiece('S', 1, 2, 2, new int[] { W + W + 1, W, W + 1, 0 }));
        result.put("Z-0", new GamePiece('Z', 0, 2, 3, new int[] { W + 1, W + 2, 0, 1 }));
        result.put("Z-1", new GamePiece('Z', 1, 2, 2, new int[] { W + W, W, W + 1, 1 }));
        result.put("L-0", new GamePiece('L', 0, 4, 2, new int[] { W + W, W + W + 1, W, 0 }));
        result.put("L-1", new GamePiece('L', 1, 4, 3, new int[] { W, W + 1, W + 2, 2 }));
        result.put("L-2", new GamePiece('L', 2, 4, 2, new int[] { W + W + 1, W + 1, 0, 1 }));
        result.put("L-3", new GamePiece('L', 3, 4, 3, new int[] { W, 0, 1, 2 }));
        result.put("J-0", new GamePiece('J', 0, 4, 2, new int[] { W + W, W + W + 1, W + 1, 1 }));
        result.put("J-1", new GamePiece('J', 1, 4, 3, new int[] { W + 2, 0, 1, 2 }));
        result.put("J-2", new GamePiece('J', 2, 4, 2, new int[] { W + W, W, 0, 1 }));
        result.put("J-3", new GamePiece('J', 3, 4, 3, new int[] { W, W + 1, W + 2, 0 }));
        return result;
    }
}

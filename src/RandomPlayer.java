import game.Command;
import game.GameState;
import game.IPlayer;
import game.StateSnapshot;

import java.util.Random;

public class RandomPlayer implements IPlayer {
    private Random random;

    @Override
    public void init() {
        random = new Random(0);
    }

    @Override
    public Command step(StateSnapshot snapshot) {
        var rotation = random.nextInt(snapshot.piece.rotations);
        var piece = snapshot.piece.rotate(rotation);
        var offset = random.nextInt(GameState.Width - piece.width + 1);

        return new Command(offset, rotation);
    }
}

package game;

public class GameRunner {
    private final GameState state;
    private final StateSnapshot snapshot;
    private final IPlayer player;

    public GameRunner(int seed, IPlayer player) {
        this.state = new GameState(seed);
        this.snapshot = new StateSnapshot();
        this.player = player;

        player.init();
    }

    public boolean step(IRender render) {
        try {
            state.copyTo(snapshot);
            final Command command = player.step(snapshot);
            if (render != null) {
                render.draw(snapshot, command);
            }
            state.apply(command);
            return true;
        } catch (Exception e) {
            if (render != null) {
                render.gameover(e);
            }
            return false;
        }
    }

    public int getScore() {
        return state.getScore();
    }
}

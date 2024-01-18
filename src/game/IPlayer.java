package game;

public interface IPlayer {
    /** Initialization before each next game */
    void init();
    Command step(StateSnapshot snapshot);
}

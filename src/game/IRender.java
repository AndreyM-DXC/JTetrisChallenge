package game;

public interface IRender {
    void draw(StateSnapshot snapshot, Command command);
    void gameover(Exception e);
}

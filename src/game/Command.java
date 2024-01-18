package game;

public class Command {
    /** Horizontal offset */
    public final int offset;
    /** Index of rotation */
    public final int rotation;

    public Command(int offset, int rotation) {
        this.offset = offset;
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "offset: " + offset + " rotation: " + rotation;
    }
}

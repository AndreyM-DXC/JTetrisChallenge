import game.Command;
import game.IRender;
import game.StateSnapshot;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class GameUI extends JPanel implements IRender {
    private final Image tiles = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("tiles.png"));
    private final Font font = new Font("System", Font.BOLD, 12);
    private final byte[] screen = new byte[10 * 26];
    private String score = "Score: 0";

    public GameUI() {
        setPreferredSize(new Dimension(26 * 10, 26 * 26));
        Arrays.fill(screen, 0, 50, (byte) 3);
        Arrays.fill(screen, 50, 60, (byte) 2);
    }

    @Override
    public void draw(StateSnapshot snapshot, Command command) {
        score = "Score: " + snapshot.score;

        Arrays.fill(screen, 0, 50, (byte) 3);
        for (var i : snapshot.piece.rotate(command.rotation).getLayout(command.offset)) {
            screen[i + 10] = 1;
        }
        for (int i = 0; i < snapshot.board.length; i++) {
            screen[i + 60] = (byte)(snapshot.board[i] ? 1 : 0);
        }
        repaint();
    }

    @Override
    public void gameover(Exception e) {
        System.err.println(e.toString());
        score += " Game Over!";
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0, idx = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++, idx++) {
                drawTile(g, j, i, screen[idx]);
            }
        }
        drawText(g, score);
    }

    private void drawTile(Graphics g, int x, int y, int tile) {
        g.drawImage(tiles,
                x * 26, y * 26, x * 26 + 26, y * 26 + 26,
                tile * 26, 0, tile * 26 + 26, 26, this);
    }

    private void drawText(Graphics g, String text) {
        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(text, 5, (int)g.getFontMetrics().getStringBounds(text, g).getHeight());
    }
}

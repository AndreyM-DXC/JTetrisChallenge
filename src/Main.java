import game.GameRunner;
import game.IPlayer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    public static final int TickTime = 1000;

    public static void main(String[] args) {
        final GameUI ui = new GameUI();
        final IPlayer player = new RandomPlayer();
        final GameRunner game = new GameRunner(1234, player);

        JFrame frame = new JFrame("JTetris Challenge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(ui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        final Timer timer = new Timer(TickTime, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.step(ui)) {
                    ((Timer)e.getSource()).stop();
                }
            }
        });
        timer.setInitialDelay(TickTime);
        timer.start();
    }
}
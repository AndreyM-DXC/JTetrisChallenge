import game.GameRunner;
import game.IPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Tournament {
    public static void main(String[] args) {
        // Tournament seeds will be generated via random.org
        final int[] seeds = IntStream.range(0, 100).toArray();
        final IPlayer player = new RandomPlayer();

        var stats = new ArrayList<Stat>();
        var score = 0;

        for (var seed : seeds) {
            var game = new GameRunner(seed, player);

            while (game.step(null)) {
            }

            var stat = new Stat(seed, game.getScore());
            score += stat.score;
            stats.add(stat);

            System.out.println(stat);
        }

        var min = stats.stream().min(Comparator.comparingInt(x -> x.score)).get();
        var max = stats.stream().max(Comparator.comparingInt(x -> x.score)).get();

        System.out.println();
        System.out.println("Final Score: " + score);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
    }

    private static class Stat {
        public final int round;
        public final int score;

        public Stat(int round, int score) {
            this.round = round;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Score: " + score + " Round: " + round;
        }
    }
}

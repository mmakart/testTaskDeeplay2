import java.util.Arrays;
import java.util.Random;

public class Task3 {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        int[] first = { 1, 2, 3 };
        int[] second = { 4, 5, 5 };
        int tossCount = 1000;

        System.out.println("Первый игрок загадал: " + Arrays.toString(first));
        System.out.println("Второй игрок загадал: " + Arrays.toString(second));
	System.out.println("Количество бросков: " + tossCount);

        double[] probabilities = calculateProbability(first, second, tossCount);
        System.out.printf("Вероятность выиграть у первого игрока: %.2f%n", probabilities[0]);
        System.out.printf("Вероятность выиграть у второго игрока: %.2f%n", probabilities[1]);
        System.out.printf("Вероятность ничьи: %.2f%n", probabilities[2]);
    }

    private static double[] calculateProbability(int[] seq1, int[] seq2, int tossCount) {
        int totalCount = 0;
        int firstWinCount = 0;
        int secondWinCount = 0;
        int drawCount = 0;

        for (int i = 0; i < 10000; i++) {
            int[] result = calculateScore(seq1, seq2, tossCount);
            if (result[0] > result[1]) {
                firstWinCount++;
            } else if (result[1] > result[0]) {
                secondWinCount++;
            } else {
                drawCount++;
            }
            totalCount++;
        }

        double[] result = new double[3];
        result[0] = (double) firstWinCount / totalCount;
        result[1] = (double) secondWinCount / totalCount;
        result[2] = (double) drawCount / totalCount;
        return result;
    }

    private static int[] calculateScore(int[] seq1, int[] seq2, int tossCount) {
        int[] tosses = rand.ints(tossCount, 1, 7).toArray();

        int[] scores = new int[2];
        scores[0] = calculateScoreFor(seq1, tosses);
        scores[1] = calculateScoreFor(seq2, tosses);
        return scores;
    }
    
    private static int calculateScoreFor(int[] seq, int[] tosses) {
        int score = 0;

        for (int i = 0; i < tosses.length - 2; ) {
            if (tosses[i] == seq[0]
                    && tosses[i + 1] == seq[1]
                    && tosses[i + 2] == seq[2]) {
                score++;
                i += 3;
            } else {
                i++;
            }
        }

        return score;
    }
}

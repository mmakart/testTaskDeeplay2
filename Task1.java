import java.util.Arrays;
import java.util.Random;
import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Task1 {
    private static final int SIZE = 40;
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        int[] integers = rand.ints(SIZE, MIN, MAX)
                .toArray();

        System.out.println(Arrays.toString(integers));

        IntStream odds = Arrays.stream(integers)
                .filter(n -> n % 2 == 1)
                .sorted();

        IntStream zeros = Arrays.stream(integers)
                .filter(n -> n == 0);

        IntStream others = Arrays.stream(integers)
                .filter(n -> n != 0 && n % 2 == 0)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue);

        int[] result = Stream.of(odds, zeros, others)
                .flatMapToInt(stream -> stream)
                .toArray();

        System.out.println(Arrays.toString(result));
    }
}

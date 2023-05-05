import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Task2 {
    private static final int SIZE = 30;
    private static final int MIN = 0;
    private static final int MAX = 10;
    private static final Random rand = new Random();

    public static void main(String[] args) {
        int[] numbers = rand.ints(SIZE, MIN, MAX)
                .toArray();

        Map<Integer, Long> counter = Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        long maxFrequency = counter.values().stream()
                .mapToLong(Long::longValue)
                .max()
                .getAsLong();

        int[] mostFrequentNumbers = counter.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .mapToInt(entry -> entry.getKey())
                .toArray();

        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(mostFrequentNumbers));
    }
}

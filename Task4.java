import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task4 {
    public static void main(String[] args) {
        int[] numbers = { 10, 8, -1, 17, 9, 8 };
        int groupCount = 3;

        System.out.println(Arrays.toString(numbers));
        List<List<Integer>> groupsWithEqualSum = separateByGroups(numbers, groupCount);
        System.out.println(groupsWithEqualSum != null ? groupsWithEqualSum : "невозможно");
    }

    private static List<List<Integer>> separateByGroups(int[] numbers, int groupCount) {
        int permutationsCount = (int) Math.pow(groupCount, numbers.length);

        for (int permutationId = 0; permutationId < permutationsCount; permutationId++) {

            List<Integer> permutation = intToDigitsOfBase(permutationId, groupCount);

            List<List<Integer>> groups = new ArrayList<>();
            IntStream.range(0, groupCount).forEach(n -> groups.add(new ArrayList<>()));
            
            for (int i = 0; i < numbers.length; i++) {
                int pos = i < permutation.size() ? permutation.get(i) : 0;
                groups.get(pos).add(numbers[i]);
            }

            int sum = 0;
            int oldSum = sum;
            boolean allEqual = false;

            for (int i = 0; i < groups.size(); i++) {
                allEqual = true;
                sum = groups.get(i).stream()
                        .mapToInt(Integer::intValue)
                        .sum();

                if (i != 0 && sum != oldSum) {
                    allEqual = false;
                    break;
                }

                oldSum = sum;
            }

            if (allEqual) {
                return groups;
            }
        }

        return null;
    }

    private static List<Integer> intToDigitsOfBase(int number, int base) {
        if (number == 0) {
            return new ArrayList<>(Arrays.asList(0));
        }

        List<Integer> digits = new ArrayList<>();
        while (number != 0) {
            int digit = number % base;
            digits.add(digit);
            number /= base;
        }
        return digits;
    }
}

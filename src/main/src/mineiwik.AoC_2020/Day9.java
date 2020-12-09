package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
 * Advent of Code 2020 - Day 9 - Encoding Error
 */
public class Day9 extends Day {

    private final long[] numbers;
    private final int PRE_NUMS = 25;

    Day9(int day) throws IOException {
        super(day);
        List<String> lines = Arrays.asList(input.split("\\r?\\n"));
        numbers = new long[lines.size()];
        for (int i = 0; i < lines.size(); i++){
            numbers[i] = Long.parseLong(lines.get(i));
        }
    }

    public String firstStar() {
        final HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < PRE_NUMS; i++){
            set.add(numbers[i]);
        }
        return String.valueOf(getError(set, PRE_NUMS));
    }

    public String secondStar() {
        final HashSet<Long> set = new HashSet<>();
        for (int i = 0; i < PRE_NUMS; i++){
            set.add(numbers[i]);
        }
        long invalid = getError(set, PRE_NUMS);

        long sum = 0;
        int j = 0;
        HashSet<Long> contiguousList = new HashSet<>();

        for (long number : numbers) {
            if (number >= invalid) continue;
            contiguousList.add(number);
            sum += number;
            while (sum > invalid) {
                sum -= numbers[j];
                contiguousList.remove(numbers[j]);
                j++;
            }
            if (sum == invalid) break;
        }

        return String.valueOf(Collections.min(contiguousList) + Collections.max(contiguousList));
    }


    private long getError(HashSet<Long> set, int preNums) {
        int begin = 0;
        long next;

        while ((begin + preNums) < numbers.length) {
            next = numbers[begin + preNums];
            if (!containsSum(set, preNums, next, begin)) return next;
            set.remove(numbers[begin]);
            set.add(next);
            begin++;
        }
        return 0;
    }

    private boolean containsSum(HashSet<Long> set, int preNums, long next, int begin) {
        for (int i = begin; i < (begin + preNums); i++) {
            long tmp = next - numbers[i];
            if (set.contains(tmp)) return true;
        }
        return false;
    }

}

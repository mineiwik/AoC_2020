package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Advent of Code 2020 - Day 1 - Report Repair
 */
public class Day1 extends Day {

    private final int size;
    private final ArrayList<Integer> numbers;

    Day1(int day) throws IOException {
        super(day);
        String[] lines = input.split("\\r?\\n");
        numbers = new ArrayList<>();
        for (String line : lines) {
            numbers.add(Integer.parseInt(line));
        }

        Collections.sort(numbers);
        size = numbers.size();
    }

    public String firstStar() {
        // EDIT: Improved this algorithm to have a complexity of O(n)
        // numbers must be sorted (with merge sort the time complexity is O(n*log(n)))
        // Therefore the total time complexity from sorting to finding the two numbers is O(n*log(n))

        int left = 0;
        int right = size - 1;

        while(left < right){
            int result = numbers.get(left) + numbers.get(right);
            if (result == 2020) return String.valueOf(numbers.get(left) * numbers.get(right));
            else if (result < 2020) left++;
            else right--;
        }
        return "no solution found";
    }

    public String secondStar() {
        // EDIT: Improved this algorithm to have a complexity of O(n^2)
        // numbers must be sorted (with merge sort the time complexity is O(n*log(n)))
        // Therefore the total time complexity from sorting to finding the triplet is O(n^2)

        int left, right;

        for (int i = 0; i < numbers.size(); i++){

            left = i + 1;
            right = size - 1;

            while(left < right){
                int result = numbers.get(i) + numbers.get(left) + numbers.get(right);
                if(result == 2020) return String.valueOf(numbers.get(i) * numbers.get(left) * numbers.get(right));
                else if (result < 2020) left++;
                else right--;
            }
        }
        return "no solution found";
    }
}

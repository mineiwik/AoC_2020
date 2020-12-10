package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Advent of Code 2020 - Day 10 - Adapter Array
 */
public class Day10 extends Day {

    private final ArrayList<Integer> adapters;

    Day10(int day) throws IOException {
        super(day);
        String[] lines = input.split("\\r?\\n");
        adapters = new ArrayList<>();
        for (String line : lines) {
            adapters.add(Integer.parseInt(line));
        }
        Collections.sort(adapters);
    }

    public String firstStar() {

        int sum1 = 0;
        int sum3 = 0;
        int previous = 0;
        int difference;

        for (int adapter : adapters){
            difference = adapter - previous;
            if (difference == 1) sum1++;
            else if (difference == 3) sum3++;

            previous = adapter;
        }
        return String.valueOf(sum1 * (sum3 + 1));
    }

    public String secondStar() {
        // Assuming there are only differences of 1 and 3
        ArrayList<ArrayList<Integer>> subLists = new ArrayList<>();
        int previous = 0;
        int difference;
        int i = 0;
        long result = 1;
        subLists.add(new ArrayList<>());
        subLists.get(0).add(0);
        for (int adapter : adapters){
            difference = adapter - previous;
            if (difference >= 3){
                i++;
                subLists.add(new ArrayList<>());
            }
            subLists.get(i).add(adapter);
            previous = adapter;
        }

        for (ArrayList<Integer> subList : subLists){
            result *= series(subList.size());
        }

        return String.valueOf(result);
    }

    private int series(int n) {
        if (n == 0) return 0;
        if (n < 3) return 1;
        return series(n-1) + series(n- 2) + series(n - 3);
    }
}

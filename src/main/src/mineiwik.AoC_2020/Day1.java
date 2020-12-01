package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Advent of Code 2020 - Day 1 - Report Repair
 */
public class Day1 extends Day {

    private final String input;
    private final String[] lines;
    private final int size;
    private ArrayList<Integer> numbers;

    Day1() throws IOException {
        String fileName = "day_1_input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        if (file.exists()){
            //Read File Content
            input = new String(Files.readAllBytes(file.toPath()));
        } else input = "";

        lines = input.split(System.getProperty("line.separator"));

        numbers = new ArrayList<Integer>();
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

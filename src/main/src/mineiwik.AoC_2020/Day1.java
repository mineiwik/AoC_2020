package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/*
 * Advent of Code 2020 - Day 1 - Report Repair
 */
public class Day1 extends Day {

    private final String input;
    private final String[] lines;
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
    }

    public String firstStar() {
        // Some O(n^2) algorithm...
        for (int i : numbers) {
            for (int j : numbers) {
                if (i + j == 2020) {
                    return String.valueOf(i * j);
                }
            }
        }
        return "no solution found";
    }

    public String secondStar() {
        // Some O(n^3) algorithm........
        for (int i : numbers){
            for (int j : numbers){
                for (int k : numbers){
                    if (i + j + k == 2020){
                        return String.valueOf(i * j * k);
                    }
                }

            }
        }
        return "no solution found";
    }
}

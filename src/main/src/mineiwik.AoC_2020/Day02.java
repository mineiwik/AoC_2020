package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Advent of Code 2020 - Day 2 - Password Philosophy
 */
public class Day02 extends Day {

    private final ArrayList<String[]> attempts;
    private int sum, first, last;
    private char letter;

    Day02(String day) throws IOException {
        super(day);
        String[] lines = input.split("\\r?\\n");
        attempts = new ArrayList<>();
        for (String line: lines){
            String[] elements = line.split(" ");
            attempts.add(elements);
        }
        sum = 0;
    }

    public String firstStar() {

        // Time complexity: O(n^2)

        sum = 0;
        for (String[] attempt : attempts){
            int occurrences = 0;
            String[] boundaries = attempt[0].split("-");
            first = Integer.parseInt(boundaries[0]);
            last = Integer.parseInt(boundaries[1]);
            letter = attempt[1].charAt(0);
            char[] password = attempt[2].toCharArray();
            for (char character : password){
                if (character == letter) occurrences++;
            }
            if (occurrences >= first && occurrences <= last) sum++;
        }

        return String.valueOf(sum);
    }

    public String secondStar() {

        // Time complexity: O(n)

        sum = 0;
        for (String[] attempt : attempts){
            String[] boundaries = attempt[0].split("-");
            first = Integer.parseInt(boundaries[0]) - 1;
            last = Integer.parseInt(boundaries[1]) - 1;
            letter = attempt[1].charAt(0);
            char[] password = attempt[2].toCharArray();
            if (password[first] == letter ^ password[last] == letter) sum++;
        }

        return String.valueOf(sum);
    }
}

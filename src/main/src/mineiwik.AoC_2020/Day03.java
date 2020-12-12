package mineiwik.AoC_2020;

import java.io.IOException;

/*
 * Advent of Code 2020 - Day 3 - Toboggan Trajectory
 */
public class Day03 extends Day {

    String[] lines;

    Day03(String day) throws IOException {
        super(day);
        lines = input.split("\\r?\\n");
    }

    private long slope (int down, int right){
        long trees = 0;
        int column = 0;
        final char TREE = '#';
        for (int row = 0; row < lines.length; row += down){
            if (lines[row].charAt(column) == TREE) trees++;
            column += right;
            if (column >= lines[row].length()) column -= lines[row].length();

        }
        return trees;
    }

    public String firstStar() {
        return String.valueOf(slope(1,3));
    }

    public String secondStar() {
        long product = slope(1,1) * slope(1,3) * slope(1,5) * slope(1,7) * slope(2,1);
        return String.valueOf(product);
    }
}

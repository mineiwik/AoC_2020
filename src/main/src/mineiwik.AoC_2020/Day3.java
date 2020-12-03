package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/*
 * Advent of Code 2020 - Day 3 - Toboggan Trajectory
 */
public class Day3 extends Day {

    private final String[] lines;
    private final char TREE = '#';

    Day3() throws IOException {
        String fileName = "day_3_input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        String input;

        if (file.exists()){
            //Read File Content
            input = (new String(Files.readAllBytes(file.toPath())));
        } else input = "";

        lines = input.split(System.getProperty("line.separator"));

        System.out.println(lines.length);
        System.out.println(lines[0].length());

    }

    private long slope (int down, int right){
        long trees = 0;
        int column = 0;
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

package mineiwik.AoC_2020;

import java.io.IOException;

public class Main {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.printf("%sAdvent %sof %sCode %s2020%s\n%n", ANSI_GREEN, ANSI_RED, ANSI_GREEN, ANSI_RED, ANSI_RESET);
        try {
            (new Day01("01")).run();
            (new Day02("02")).run();
            (new Day03("03")).run();
            (new Day04("04")).run();
            (new Day05("05")).run();
            (new Day06("06")).run();
            (new Day07("07")).run();
            (new Day08("08")).run();
            (new Day09("09")).run();
            (new Day10("10")).run();
            (new Day11("11")).run();
            (new Day12("12")).run();
            (new Day13("13")).run();
            (new Day14("14")).run();
            (new Day15("15")).run();
            (new Day16("16")).run();
            (new Day17("17")).run();
            (new Day18("18")).run();
            (new Day21("21")).run();
        } catch (IOException e){
            throw new IOException(e);
        }
    }
}

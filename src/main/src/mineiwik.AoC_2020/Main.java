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
            (new Day1(1)).run();
            (new Day2(2)).run();
            (new Day3(3)).run();
            (new Day4(4)).run();
            (new Day5(5)).run();
            (new Day6(6)).run();
        } catch (IOException e){
            throw new IOException(e);
        }
    }
}

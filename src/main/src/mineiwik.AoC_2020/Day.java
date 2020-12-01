package mineiwik.AoC_2020;

import java.time.Instant;
import java.time.Duration;

public abstract class Day {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public abstract String firstStar();
    public abstract String secondStar();

    public void run() {
        System.out.println(String.format("%sRunning %s%s", ANSI_GREEN, this.getClass().getName(), ANSI_RESET));

        Instant start1 = Instant.now();
        System.out.println(String.format("%s" + this.firstStar() + "%s", ANSI_YELLOW, ANSI_RESET));
        Instant end1 = Instant.now();
        long runtimePart1 = Duration.between(start1, end1).toMillis();
        System.out.println(String.format("Part %s1%s took %d ms", ANSI_YELLOW, ANSI_RESET, runtimePart1));

        Instant start2 = Instant.now();
        System.out.println(String.format("%s" + this.secondStar() + "%s", ANSI_YELLOW, ANSI_RESET));
        Instant end2 = Instant.now();
        long runtimePart2 = Duration.between(start2, end2).toMillis();
        System.out.println(String.format("Part %s2%s took %d ms", ANSI_YELLOW, ANSI_RESET, runtimePart2));
        System.out.println(String.format("\n%s", ANSI_RESET));
    }
}

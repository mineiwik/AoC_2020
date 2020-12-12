package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Instant;
import java.time.Duration;
import java.util.Objects;

public abstract class Day {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public abstract String firstStar();
    public abstract String secondStar();

    public final String input;

    protected Day(String day) throws IOException {
        String fileName = "day_" + day + "_input.txt";
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        if (file.exists()){
            //Read File Content
            input = new String(Files.readAllBytes(file.toPath()));
        } else input = "";
    }

    public void run() {
        System.out.printf("%sRunning %s%s%n", ANSI_GREEN, this.getClass().getName(), ANSI_RESET);

        Instant start1 = Instant.now();
        System.out.printf("%s" + this.firstStar() + "%s%n", ANSI_YELLOW, ANSI_RESET);
        Instant end1 = Instant.now();
        long runtimePart1 = Duration.between(start1, end1).toMillis();
        System.out.printf("Part %s1%s took %d ms%n", ANSI_YELLOW, ANSI_RESET, runtimePart1);

        Instant start2 = Instant.now();
        System.out.printf("%s" + this.secondStar() + "%s%n", ANSI_YELLOW, ANSI_RESET);
        Instant end2 = Instant.now();
        long runtimePart2 = Duration.between(start2, end2).toMillis();
        System.out.printf("Part %s2%s took %d ms%n", ANSI_YELLOW, ANSI_RESET, runtimePart2);
        System.out.printf("\n%s%n", ANSI_RESET);
    }
}

package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

        long timerStart = System.nanoTime();
        System.out.printf("%s" + this.firstStar() + "%s%n", ANSI_YELLOW, ANSI_RESET);
        long timeSpent = (System.nanoTime() - timerStart) / 1000;
        if(timeSpent < 1000) System.out.printf("Part %s1%s took %d µs%n", ANSI_YELLOW, ANSI_RESET, timeSpent);
        else if(timeSpent < 1000000) System.out.printf("Part %s1%s took %f ms%n", ANSI_YELLOW, ANSI_RESET, timeSpent / 1000.0);
        else System.out.printf("Part %s1%s took %f s%n", ANSI_YELLOW, ANSI_RESET, timeSpent / 1000000.0);

        timerStart = System.nanoTime();
        System.out.printf("%s" + this.secondStar() + "%s%n", ANSI_YELLOW, ANSI_RESET);
        timeSpent = (System.nanoTime() - timerStart) / 1000;
        if(timeSpent < 1000) System.out.printf("Part %s2%s took %d µs%n", ANSI_YELLOW, ANSI_RESET, timeSpent);
        else if(timeSpent < 1000000) System.out.printf("Part %s2%s took %f ms%n", ANSI_YELLOW, ANSI_RESET, timeSpent / 1000.0);
        else System.out.printf("Part %s2%s took %f s%n", ANSI_YELLOW, ANSI_RESET, timeSpent / 1000000.0);
        System.out.printf("\n%s%n", ANSI_RESET);
    }
}

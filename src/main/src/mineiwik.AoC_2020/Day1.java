package mineiwik.AoC_2020;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Day1 extends Day {

    private final String input;

    Day1() throws IOException {
        String fileName = "day_1_input.txt";
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource(fileName).getFile());

        if (file.exists()){
            //Read File Content
            input = new String(Files.readAllBytes(file.toPath()));
            return;
        }

        input = "";
    }

    public void firstStar() {
        // Code for part 1 of day 1
    }

    public void secondStar() {
        // Code for part 2 of day 1
    }
}

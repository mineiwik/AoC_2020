package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Advent of Code 2020 - Day 15 - Rambunctious Recitation
 */
public class Day15 extends Day {

    private final ArrayList<Integer> starting;

    Day15(String day) throws IOException {
        super(day);
        starting = new ArrayList<>();
        for (String i : input.split(",")) {
            starting.add(Integer.parseInt(i));
        }
    }

    public String firstStar() {
        return String.valueOf(getLatestNumberSpoken(2020));
    }

    public String secondStar() {
        // Less inefficient than before...
        return String.valueOf(getLatestNumberSpoken(30000000));
    }

    private int getLatestNumberSpoken(int endTurn) {
        Map<Integer, Integer> memory = new HashMap<>();

        int turn = 1;

        for (Integer number : starting) {
            memory.put(number, turn++);
        }

        int latest = starting.get(starting.size() - 1);

        while (turn <= endTurn) {
            int newLatest = memory.containsKey(latest) ? turn - 1 - memory.get(latest) : 0;
            memory.put(latest, turn - 1);
            latest = newLatest;
            turn++;
        }
        return latest;
    }

}

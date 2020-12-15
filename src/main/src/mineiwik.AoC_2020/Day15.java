package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

        HashMap<Integer, ArrayList<Integer>> memory = new HashMap<>();

        int turn = 1;
        int latest = 0;
        int current;

        while (turn < 2021) {
            if (turn - 1 < starting.size()) {
                memory.put(starting.get(turn - 1), new ArrayList<>(Collections.singletonList(turn)));
                latest = starting.get(turn - 1);
            }
            else if (memory.containsKey(latest)){
                if (memory.get(latest).size() == 1){
                    current = 0;
                }
                else {
                    current = memory.get(latest).get(memory.get(latest).size() - 1) - memory.get(latest).get(memory.get(latest).size() - 2);
                }
                if (memory.containsKey(current)) memory.get(current).add(turn);
                else memory.put(current, new ArrayList<>(Collections.singletonList(turn)));
                latest = current;
            }
            else memory.put(latest, new ArrayList<>(Collections.singletonList(turn - 1)));
            turn++;
        }

        return String.valueOf(latest);
    }

    public String secondStar() {

        // Totally inefficient!

        HashMap<Integer, ArrayList<Integer>> memory = new HashMap<>();

        int turn = 1;
        int latest = 0;
        int current;

        while (turn < 30000001) {
            if (turn - 1 < starting.size()) {
                memory.put(starting.get(turn - 1), new ArrayList<>(Collections.singletonList(turn)));
                latest = starting.get(turn - 1);
            }
            else if (memory.containsKey(latest)){
                if (memory.get(latest).size() == 1){
                    current = 0;
                }
                else {
                    current = memory.get(latest).get(memory.get(latest).size() - 1) - memory.get(latest).get(memory.get(latest).size() - 2);
                }
                if (memory.containsKey(current)) memory.get(current).add(turn);
                else memory.put(current, new ArrayList<>(Collections.singletonList(turn)));
                latest = current;
            }
            else memory.put(latest, new ArrayList<>(Collections.singletonList(turn - 1)));
            turn++;
        }

        return String.valueOf(latest);
    }

}

package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Advent of Code 2020 - Day 14 - Docking Data
 */
public class Day14 extends Day {

    private final String[] program;
    private HashMap<Integer, Long> memory;

    Day14(String day) throws IOException {
        super(day);
        program = input.split("\\r?\\n");

    }

    public String firstStar() {
        String mask = "";
        int address;
        long value;
        String[] current;
        memory = new HashMap<>();
        for (String task : program) {
            if (task.startsWith("mask")) {
                mask = task.split(" = ")[1];
                continue;
            }
            current = task.split(" = ");
            address = Integer.parseInt(current[0].substring(current[0].indexOf("[") + 1, current[0].indexOf("]")));
            value = Long.parseLong(current[1]);
            long result = 0L;
            long pos = 1L;
            for (int i = 0; i < mask.length(); i++){
                final char c = mask.charAt(mask.length() - (i + 1));
                if (c == 'X') result += value & pos;
                if (c == '1') result += pos;
                pos *= 2;
            }
            memory.put(address, result);
        }

        long sum = 0L;
        for (Long val : memory.values()) sum += val;

        return String.valueOf(sum);
    }

    public String secondStar() {
        return "not solved yet!";
    }


}

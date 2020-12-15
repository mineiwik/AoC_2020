package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Advent of Code 2020 - Day 14 - Docking Data
 */
public class Day14 extends Day {

    private final String[] program;

    Day14(String day) throws IOException {
        super(day);
        program = input.split("\\r?\\n");

    }

    public String firstStar() {
        HashMap<Integer, Long> memory = new HashMap<>();
        String mask = "";
        int address;
        long value;
        String[] current;
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
        HashMap<Long, Long> memory = new HashMap<>();
        String mask = "";
        StringBuilder address;
        long value;
        String[] current;
        for (String task : program) {
            if (task.startsWith("mask")) {
                mask = task.split(" = ")[1];
                continue;
            }
            address = new StringBuilder();
            current = task.split(" = ");
            String temp = Long.toBinaryString(Long.parseLong(current[0].substring(current[0].indexOf("[") + 1, current[0].indexOf("]"))));
            address.append("0".repeat(Math.max(0, 36 - temp.length())));
            address.append(temp);
            value = Long.parseLong(current[1]);
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < mask.length(); i++){
                final char c = mask.charAt(i);
                if (c == 'X' || c == '1') result.append(c);
                else result.append(address.charAt(i));
            }
            ArrayList<String> addresses = getAllAddresses(String.valueOf(result), memory);
            for (String i : addresses) {
                memory.put(Long.parseLong(i, 2), value);
            }
        }
        long sum = 0L;
        for (Long val : memory.values()) sum += val;

        return String.valueOf(sum);
    }


    private ArrayList<String> getAllAddresses(String address, HashMap<Long, Long> memory) {
        int countX = address.length() - address.replace("X", "").length();
        ArrayList<String> addresses = new ArrayList<>();
        addresses.add(address);
        for (int i = 0; i < (int) Math.pow(2, countX) - 1; i++) {
            int index = addresses.get(0).indexOf('X');
            String current = addresses.remove(0);
            addresses.add(current.substring(0, index) + "0" + current.substring(index + 1));
            addresses.add(current.substring(0, index) + "1" + current.substring(index + 1));

        }
        return addresses;
    }

}

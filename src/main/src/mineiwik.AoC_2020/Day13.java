package mineiwik.AoC_2020;

import java.io.IOException;
import static java.util.stream.IntStream.range;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

/*
 * Advent of Code 2020 - Day 13 - Shuttle Search
 */
public class Day13 extends Day {

    private final int time;
    private final String[] buses;

    Day13(String day) throws IOException {
        super(day);
        time = Integer.parseInt(input.split("\\r?\\n")[0]);
        buses = input.split("\\r?\\n")[1].split(",");
    }

    public String firstStar() {
        int smallest = Integer.MAX_VALUE;
        int bestBus = 0;
        for (String bus : buses) {
            if (bus.equals("x")) continue;
            int busID = Integer.parseInt(bus);
            if (busID - (time % busID) < smallest) {
                smallest = busID - (time % busID);
                bestBus = busID;
            }
        }
        return String.valueOf(smallest * bestBus);
    }

    public String secondStar() {
        long product = 1;

        for (String bus : buses) {
            if (bus.equals("x")) continue;
            product *= Long.parseLong(bus);
        }

        long lcm = 1;
        int currentBus = 0;

        for (long i = 0; i < product; i+= lcm){
            while (buses[currentBus].equals("x") && currentBus < buses.length - 1) currentBus++;
            if ((i + currentBus) % Long.parseLong(buses[currentBus]) == 0){
                lcm *= Long.parseLong(buses[currentBus]);
                if (currentBus == buses.length - 1) return String.valueOf(i);
                currentBus++;
            }
        }
        return "";
    }



}

package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Advent of Code 2020 - Day 5 - Binary Boarding
 */
public class Day05 extends Day {

    private final ArrayList<Integer> seats = new ArrayList<>();

    Day05(String day) throws IOException {
        super(day);
        String[] lines = input.split("\\r?\\n");
        for(String line : lines){
            seats.add(getSeat(line));
        }

        // Sorting O(n*log(n))
        Collections.sort(seats);
    }

    private int getSeat(String line){
        int seatBinary = 0;
        for (char i : line.toCharArray()){
            switch (i){
                case ('B'): case ('R'):
                    seatBinary++;
                    break;
            }
            seatBinary = seatBinary << 1;
        }
        return seatBinary >> 1;
    }

    public String firstStar() {

        // ArrayList seat must be sorted O(n*log(n)
        // To find the highest seat id we have a complexity of O(1)
        // In total (with an unsorted list) O(n*log(n))

        return String.valueOf(seats.get(seats.size() - 1));
    }

    public String secondStar() {

        // ArrayList seat must be sorted O(n*log(n)
        // To find MY seat id we have a complexity of O(n)
        // In total (with an unsorted list) O(n*log(n))

        int lastID = 0;

        for (int seat : seats) {
            if (lastID + 1 == seat - 1) return String.valueOf(seat - 1);
            lastID = seat;
        }

        return "nothing found";
    }
}

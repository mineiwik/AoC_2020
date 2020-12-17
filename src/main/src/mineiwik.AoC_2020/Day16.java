package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/*
 * Advent of Code 2020 - Day 16 - Ticket Translation
 */
public class Day16 extends Day {

    private final Set<Integer> rules;
    private final ArrayList<Integer> nearbyTickets;


    Day16(String day) throws IOException {
        super(day);
        String[] groups = input.split("\\n\\s*\\n");
        rules = new HashSet<>();
        for (String rule : groups[0].split("\\r?\\n")) {
            String[] ranges = rule.strip().split(": ")[1].split(" or ");
            String[] firstRange = ranges[0].split("-");
            String[] secondRange = ranges[1].split("-");
            IntStream.range(Integer.parseInt(firstRange[0]), Integer.parseInt(firstRange[1]) + 1).forEach(rules::add);
            IntStream.range(Integer.parseInt(secondRange[0]), Integer.parseInt(secondRange[1]) + 1).forEach(rules::add);
        }
        ArrayList<Integer> myTicket = new ArrayList<>();
        for (String myNumber : groups[1].strip().split("\\r?\\n")[1].split(",")) myTicket.add(Integer.parseInt(myNumber));

        nearbyTickets = new ArrayList<>();
        for (String nearby : groups[2].strip().split("\\r?\\n")) {
            if (nearby.equals("nearby tickets:")) continue;
            for (String number : nearby.strip().split(",")) nearbyTickets.add(Integer.parseInt(number));
        }
    }

    public String firstStar() {
        long sum = 0;
        for (int nearbyNumber : nearbyTickets) {
            if (!rules.contains(nearbyNumber)) sum += nearbyNumber;
        }
        return String.valueOf(sum);
    }

    public String secondStar() {
        return "Not solved yet!";
    }

}

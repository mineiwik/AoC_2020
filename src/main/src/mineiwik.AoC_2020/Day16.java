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

    private final ArrayList<InputField> rules;
    private final Set<Integer> numbers;
    private final ArrayList<ArrayList<Integer>> nearbyTickets;
    private final ArrayList<Integer> myTicket;


    Day16(String day) throws IOException {
        super(day);
        String[] groups = input.split("\\n\\s*\\n");
        rules = new ArrayList<>();
        numbers = new HashSet<>();
        for (String rule : groups[0].split("\\r?\\n")) {
            String[] ranges = rule.strip().split(": ")[1].split(" or ");
            String[] firstRange = ranges[0].split("-");
            String[] secondRange = ranges[1].split("-");
            InputField field = new InputField();
            field.name = rule.strip().split(": ")[0].strip().split(" ")[0];
            field.min1 = Integer.parseInt(firstRange[0]);
            field.max1 = Integer.parseInt(firstRange[1]);
            field.min2 = Integer.parseInt(secondRange[0]);
            field.max2 = Integer.parseInt(secondRange[1]);
            rules.add(field);
            IntStream.range(Integer.parseInt(firstRange[0]), Integer.parseInt(firstRange[1]) + 1).forEach(numbers::add);
            IntStream.range(Integer.parseInt(secondRange[0]), Integer.parseInt(secondRange[1]) + 1).forEach(numbers::add);
        }

        myTicket = new ArrayList<>();
        for (String myNumber : groups[1].strip().split("\\r?\\n")[1].split(",")) myTicket.add(Integer.parseInt(myNumber));

        nearbyTickets = new ArrayList<>();
        for (String nearby : groups[2].strip().split("\\r?\\n")) {
            if (nearby.equals("nearby tickets:")) continue;
            ArrayList<Integer> ticket = new ArrayList<>();
            for (String number : nearby.strip().split(",")) ticket.add(Integer.parseInt(number));
            nearbyTickets.add(ticket);
        }

    }

    public String firstStar() {
        long sum = 0;
        for (int i = 0; i < nearbyTickets.size(); i++) {
            for (int nearbyNumber : nearbyTickets.get(i)) {
                if (!numbers.contains(nearbyNumber)){
                    sum += nearbyNumber;
                    nearbyTickets.remove(i);
                    i--;
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }

    public String secondStar() {


        for (InputField field : rules) {
            for (int i = 0; i < nearbyTickets.get(0).size(); i++) {
                for (int j = 0; j < nearbyTickets.size(); j++) {
                    int current = nearbyTickets.get(j).get(i);
                    if ((field.min1 > current || current > field.max1) && (field.min2 > current || current > field.max2)) break;
                    if (j == nearbyTickets.size() - 1) field.possibilities.add(i);
                }
            }
        }

        while(true) {
            int currentCorresponding = -1;
            for (InputField field : rules){
                if (field.correspondingField != -1) continue;
                if (field.possibilities.size() == 1){
                    field.correspondingField = field.possibilities.get(0);
                    currentCorresponding = field.possibilities.get(0);
                    break;
                }
            }
            if (currentCorresponding == -1) break;

            for (InputField field : rules){
                if (field.correspondingField != -1) continue;
                if (field.possibilities.contains(currentCorresponding)){
                    field.possibilities.remove((Integer) currentCorresponding);
                }
            }
        }

        long product = 1;
        for (InputField field : rules){
            if (field.name.equals("departure")) product *= myTicket.get(field.correspondingField);
        }
        return String.valueOf(product);
    }


    static class InputField {
        public String name;
        public int min1;
        public int max1;
        public int min2;
        public int max2;
        public int correspondingField = -1;
        public ArrayList<Integer> possibilities = new ArrayList<>();
    }

}

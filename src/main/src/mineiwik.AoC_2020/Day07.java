package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.HashMap;

/*
 * Advent of Code 2020 - Day 7 - Handy Haversacks
 */
public class Day07 extends Day {

    private final String[] rules;
    private final HashMap<String, String[]>  regulations;
    private final String MY_BAG = "shiny gold";

    Day07(String day) throws IOException {
        super(day);
        rules = input.split("\\r?\\n");

        regulations = new HashMap<>();

        for (String rule : rules){
            String[] bags = rule.split(" bags contain ");
            String[] innerBags = bags[1].replace(".", "").split(", ");
            regulations.put(bags[0], innerBags);
        }
    }

    public String firstStar() {
        int sum = 0;
        String currentBag;
        for (String rule : rules){
            currentBag = rule.split(" bags contain ")[0];
            if (currentBag.equals(MY_BAG)) continue;
            if (containsBag(currentBag)) sum++;
        }
        return String.valueOf(sum);
    }

    private boolean containsBag(String bag){
        if (bag.equals(MY_BAG)) return true;

        if (!regulations.containsKey(bag) || bag.equals("no other")) return false;

        String[] content = regulations.get(bag);

        for (String innerBag : content){
            if (containsBag(innerBag.split(" ",2)[1].split(" bag")[0])) return true;
        }

        return false;
    }

    public String secondStar() {
        return String.valueOf(countBags(MY_BAG));
    }

    private int countBags(String bag){
        if (!regulations.containsKey(bag)) return 0;
        int sum = 0;

        String[] content = regulations.get(bag);

        for (String innerBag : content){
            if (innerBag.equals("no other bags")) continue;
            int thisBag = Integer.parseInt(innerBag.split(" ",2)[0]);
            sum += thisBag;
            sum += thisBag * countBags(innerBag.split(" ",2)[1].split(" bag")[0]);
        }
        return sum;
    }
}

package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.HashSet;

/*
 * Advent of Code 2020 - Day 6 - Custom Customs
 */
public class Day6 extends Day {

    private final String[] groups;

    Day6(int day) throws IOException {
        super(day);
        //Split input into groups
        groups = input.split("\\n\\s*\\n");
    }


    HashSet<Character> getSet (String input){
        HashSet<Character> set = new HashSet<>();
        input.chars().forEach(i -> set.add((char) i));
        return set;
    }


    public String firstStar() {
        int sum = 0;
        for (String s : groups) {
            String group = s.replace("\n", "").replace("\r", "");
            sum += getSet(group).size();
        }
        return String.valueOf(sum);
    }

    public String secondStar() {
        int sum = 0;
        for (String group : groups) {
            String[] members = group.trim().split("\\r?\\n");
            HashSet<Character> result = new HashSet<>(getSet(members[0]));
            for (String member : members) {
                result.retainAll(getSet(member));
            }
            sum += result.size();
        }
        return String.valueOf(sum);
    }
}

package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Advent of Code 2020 - Day 8 - Handheld Halting
 */
public class Day8 extends Day {

    private final Handheld console;

    Day8(int day) throws IOException {
        super(day);
        List<String> lines = Arrays.asList(input.split("\\r?\\n"));
        console = new Handheld(lines);
    }

    public String firstStar() {
        console.reset();
        while (console.executeInstruction() == 0);
        return String.valueOf(console.getAcc());
    }

    public String secondStar() {
        return String.valueOf(console.fix());
    }

    static class Handheld {

        private int pos;
        private int acc;
        private final List<String> instructions;
        private int[] occurrences;

        Handheld(List<String> lines) {
            pos = 0;
            acc = 0;
            instructions = new ArrayList<>(lines);
            occurrences = new int[instructions.size()];
        }

        private int executeInstruction(){
            if (pos >= instructions.size()) return 1;
            if (occurrences[pos] != 0) return -1;
            String[] instruction = instructions.get(pos).split(" ");
            occurrences[pos]++;
            switch (instruction[0]){
                case "acc":
                    acc += Integer.parseInt(instruction[1]);
                    pos++;
                    break;
                case "jmp":
                    pos += Integer.parseInt(instruction[1]);
                    break;
                default:
                    pos++;
            }
            return 0;
        }

        private int fix(){
            int currentState;
            for (int i = 0; i < instructions.size(); i++){
                reset();
                currentState = 0;
                switchInstruction(i);
                while (currentState == 0) currentState = executeInstruction();
                if (currentState == 1) return acc;
                switchInstruction(i);
            }
            return 0;
        }

        private void switchInstruction(int location){
            String[] parts = instructions.get(location).split(" ");
            if (parts[0].equals("nop")) parts[0] = "jmp";
            else if (parts[0].equals("jmp")) parts[0] = "nop";
            instructions.set(location, parts[0] + " " + parts[1]);
        }

        public void reset(){
            pos = 0;
            acc = 0;
            occurrences = new int[instructions.size()];
        }

        public int getAcc(){
            return acc;
        }

    }


}

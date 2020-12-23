package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * Advent of Code 2020 - Day 23 - Crab Cups
 */
public class Day23 extends Day {

    Day23(String day) throws IOException {
        super(day);
    }

    public String firstStar() {
        final int maxValue = 9;

        int current = Character.getNumericValue(input.toCharArray()[0]);
        Map<Integer, Node> circle = buildCircle(maxValue);

        for (int i = 0; i < 100; i++) {
            current = playOneRound(circle, current, maxValue);
        }

        Node i = circle.get(1).next;
        StringBuilder output = new StringBuilder();
        while (i.value != 1) {
            output.append(i.value);
            i = i.next;
        }

        return output.toString();
    }

    public String secondStar() {
        final int maxValue = 1000000;

        int current = Character.getNumericValue(input.toCharArray()[0]);

        Map<Integer, Node> circle = buildCircle(maxValue);

        for (int i = 0; i < 10000000; i++) {
            current = playOneRound(circle, current, maxValue);
        }

        Node i = circle.get(1);

        return String.valueOf((long) i.next.value * (long) i.next.next.value);
    }

    private Map<Integer, Node> buildCircle(int maxValue) {
        Node previous = null;
        Map<Integer, Node> circle = new HashMap<>();
        for (char c : input.toCharArray()) {
            int cupNumber = Character.getNumericValue(c);
            Node next = new Node(cupNumber);
            if (previous != null) {
                previous.insertAfter(next);
            }
            previous = next;
            circle.put(cupNumber, next);
        }

        for (int i = circle.size() + 1; i <= maxValue; i++) {
            Node next = new Node(i);
            assert previous != null;
            previous.insertAfter(next);
            previous = next;
            circle.put(i, next);
        }

        return circle;
    }

    private int playOneRound(Map<Integer, Node> circle, int current, int maxValue) {
        Node currentCup = circle.get(current);
        Node firstCup = currentCup.next.remove();
        Node secondCup = currentCup.next.remove();
        Node thirdCup = currentCup.next.remove();

        int destination = current - 1;
        if (destination < 1) destination = maxValue;
        Node destinationNode = circle.get(destination);
        while (destinationNode == firstCup || destinationNode == secondCup || destinationNode == thirdCup) {
            destination = destination - 1;
            if (destination < 1) destination = maxValue;
            destinationNode = circle.get(destination);
        }

        Node destinationCup = circle.get(destination);
        destinationCup.insertAfter(thirdCup);
        destinationCup.insertAfter(secondCup);
        destinationCup.insertAfter(firstCup);

        current = currentCup.next.value;
        return current;
    }

    static class Node {
        public Node next = this;
        public Node previous = this;

        public final int value;

        public Node(int value) {
            this.value = value;
        }

        public Node remove() {
            previous.next = next;
            next.previous = previous;
            return this;
        }

        public void insertAfter(Node node) {
            next.previous = node;
            node.previous = this;
            node.next = next;
            this.next = node;
        }
    }
}
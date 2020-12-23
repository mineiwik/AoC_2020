package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Advent of Code 2020 - Day 22 - Crab Combat
 */
public class Day22 extends Day {

    private final ArrayList<Deck> decks;

    Day22(String day) throws IOException {
        super(day);
        decks = new ArrayList<>();
        byte i = 0;
        for (String deck : input.split("\\n\\s*\\n")) {
            decks.add(new Deck());
            for (String card : deck.trim().split("\\r?\\n")) {
                if (card.contains("Player")) continue;
                decks.get(i).add(new Card(Byte.parseByte(card)));
            }
            i++;
        }
    }

    public String firstStar() {

        Deck deck1 = decks.get(0);
        Deck deck2 = decks.get(1);

        while (deck1.size() != 0 && deck2.size() != 0) {
            Card card1 = deck1.remove();
            Card card2 = deck2.remove();


            if (card1.compareTo(card2) > 0) deck1.addTwo(card1, card2);
            else deck2.addTwo(card2, card1);
        }

        Deck winner = (deck1.size() != 0) ? deck1 : deck2;

        long sum = 0;

        for (int i = 0; i < winner.size(); i++) {
            sum += (long) winner.get(i).getValue() * (winner.size() - i);
        }

        return String.valueOf(sum);
    }

    public String secondStar() {
        return "Not solved yet!";
    }

    static class Deck {
        private final ArrayList<Card> deck;
        Deck() {
            deck = new ArrayList<>();
        }

        public Card get(int index) {
            return deck.get(index);
        }

        public void add(Card card) {
            deck.add(card);
        }

        public void addTwo(Card card1, Card card2){
            deck.add(card1);
            deck.add(card2);
        }

        public int size() {
            return deck.size();
        }

        public Card remove() {
            return deck.remove(0);
        }
    }

    static class Card {
        private final byte value;
        Card(byte value) {
            this.value = value;
        }

        public byte getValue() {
            return value;
        }

        public int compareTo(Card rhs) {
            return Byte.compare(value, rhs.value);
        }
    }

}
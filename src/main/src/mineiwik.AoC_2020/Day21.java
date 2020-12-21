package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.*;

/*
 * Advent of Code 2020 - Day 21 - Allergen Assessment
 */
public class Day21 extends Day {

    private final ArrayList<String> foodLines;
    private final ArrayList<String> allergenicIngredients;
    private final Map<String, String> allergenicIngredientsMap;

    Day21(String day) throws IOException {
        super(day);
        Map<String, Set<String>> food = new HashMap<>();
        foodLines = new ArrayList<>();
        for (String line : input.split("\\r?\\n")) {
            String[] parts = line.trim().split(" \\(contains ");
            Set<String> currentIngredients = new HashSet<>(Arrays.asList(parts[0].split(" ")));
            foodLines.add(parts[0].trim());
            for (String allergen : parts[1].replace(")", "").trim().split(", ")) {
                if (food.containsKey(allergen)) food.get(allergen).retainAll(new HashSet<>(currentIngredients));
                else food.put(allergen, new HashSet<>(currentIngredients));
            }
        }

        allergenicIngredients = new ArrayList<>();
        allergenicIngredientsMap = new TreeMap<>();


        do {
            for (String allergen : food.keySet()) {
                if (food.get(allergen).size() == 1) {
                    String allergenicIngredient = (String) food.get(allergen).toArray()[0];
                    allergenicIngredients.add(allergenicIngredient);
                    allergenicIngredientsMap.put(allergen, allergenicIngredient);
                    food.remove(allergen);
                    for (String allergen2 : food.keySet()) {
                        food.get(allergen2).remove(allergenicIngredient);
                    }
                    break;
                }
            }
        } while (food.size() != 0);

    }

    public String firstStar() {
        long sum = 0;

        for (String foodLine : foodLines) {
            for (String food : foodLine.split(" ")) {
                    if (!allergenicIngredients.contains(food)) sum++;
            }
        }

        return String.valueOf(sum);
    }

    public String secondStar() {

        StringBuilder canonicalDangerousIngredientList = new StringBuilder();

        for (String allergen : allergenicIngredientsMap.keySet()) {
            canonicalDangerousIngredientList.append(allergenicIngredientsMap.get(allergen)).append(",");
        }

        canonicalDangerousIngredientList.setLength(canonicalDangerousIngredientList.length() - 1);

        return canonicalDangerousIngredientList.toString();
    }

}
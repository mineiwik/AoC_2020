package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Advent of Code 2020 - Day 4 - Passport Processing
 */
public class Day04 extends Day {

    private final String[] required = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    private final ArrayList<HashMap<String,String>> maps = new ArrayList<>();

    Day04(String day) throws IOException {
        super(day);
        //Split input into chunks of possible passports
        String[] lines = input.split("\\n\\s*\\n");

        //Some parsing into HashMaps
        for (int i = 0; i < lines.length; i++){
            lines[i] = lines[i].replace("\n", " ").replace("\r", "");

            HashMap<String,String> map = new HashMap<>();
            String[] pairs = lines[i].split(" ");

            for(String pair : pairs){
                String[] values = pair.split(":");
                map.put(values[0].trim(), values[1].trim());
            }

            maps.add(map);
        }
    }

    private boolean isNummeric(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private boolean isHex(String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (c >= 48 && c <= 57 || c >= 97 && c <= 102) {
                return true;
            }
        }

        return false;
    }


    public String firstStar() {
        int sum = 0;
        boolean valid;

        for (HashMap<String,String> map : maps){
            valid = true;
            for(String key : required){
                if (map.containsKey(key)) continue;
                valid = false;
                break;
            }
            if (valid) sum++;
        }
        return String.valueOf(sum);
    }

    public String secondStar() {
        int sum = 0;
        boolean valid;

        for (HashMap<String,String> map : maps){
            valid = true;
            for(String key : required){
                if (map.containsKey(key)){
                    String value = map.get(key);
                    switch(key) {
                        case "byr":
                            if (!isNummeric(value) || value.length() != 4 || Integer.parseInt(value) < 1920 || Integer.parseInt(value) > 2002) valid = false;
                            break;
                        case "iyr":
                            if (!isNummeric(value) || value.length() != 4 || Integer.parseInt(value) < 2010 || Integer.parseInt(value) > 2020) valid = false;
                            break;
                        case "eyr":
                            if (!isNummeric(value) || value.length() != 4 || Integer.parseInt(value) < 2020 || Integer.parseInt(value) > 2030) valid = false;
                            break;
                        case "hgt":
                            String hgt = value.substring(0, value.length() - 2);
                            String unit = value.substring(value.length() - 2);
                            if (unit.equals("cm")){
                                if (!isNummeric(hgt) || Integer.parseInt(hgt) < 150 || Integer.parseInt(hgt) > 193) valid = false;
                            } else if (unit.equals("in")){
                                if (!isNummeric(hgt) || Integer.parseInt(hgt) < 59 || Integer.parseInt(hgt) > 76) valid = false;
                            } else valid = false;
                            break;
                        case "hcl":
                            char prefix = value.charAt(0);
                            String color = value.substring(1);
                            if (prefix == '#'){
                                if (color.length() != 6 || !isHex(color)) valid = false;
                            } else valid = false;
                            break;
                        case "ecl":
                            String[] possibleECLs = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                            valid = false;
                            for (String ecl : possibleECLs){
                                if (ecl.equals(value)){
                                    valid = true;
                                    break;
                                }
                            }
                            break;
                        case "pid":
                            if (!isNummeric(value) || value.length() != 9) valid = false;
                            break;
                    }
                } else valid = false;
                if (!valid) break;
            }
            if (valid) sum++;
        }
        return String.valueOf(sum);
    }
}

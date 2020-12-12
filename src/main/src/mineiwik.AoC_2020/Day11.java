package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.Arrays;

/*
 * Advent of Code 2020 - Day 11 - Seating System
 */
public class Day11 extends Day {

    private final char[][] grid;
    private final int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    Day11(String day) throws IOException {
        super(day);
        String[] lines = input.split("\\r?\\n");
        grid = new char[lines.length][lines[0].length()];
        char[] positions;
        for (int i = 0; i < lines.length; i++) {
            positions = lines[i].toCharArray();
            System.arraycopy(positions, 0, grid[i], 0, positions.length);
        }
    }

    public String firstStar() {
        char[][] current = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
        char[][] clone;
        char position;
        int neighbours;
        boolean isStable;
        do {
            clone = Arrays.stream(current).map(char[]::clone).toArray(char[][]::new);
            isStable = true;
            for (int i = 0; i < current.length; i++) {
                for (int j = 0; j < current[i].length; j++) {
                    position = current[i][j];
                    neighbours = countImmediateNeighbours(i, j, current);
                    if (position == 'L' && neighbours == 0) {
                        isStable = false;
                        clone[i][j] = '#';
                    } else if (position == '#' && neighbours >= 4) {
                        isStable = false;
                        clone[i][j] = 'L';
                    }
                }
            }
            current = clone;
        } while (!isStable);

        int occupied = 0;

        for (char[] positions : current) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] == '#') occupied++;
            }
        }

        return String.valueOf(occupied);
    }

    public String secondStar() {
        char[][] current = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
        char[][] clone;
        char position;
        int neighbours;
        boolean isStable;
        do {
            clone = Arrays.stream(current).map(char[]::clone).toArray(char[][]::new);
            isStable = true;
            for (int i = 0; i < current.length; i++) {
                for (int j = 0; j < current[i].length; j++) {
                    position = current[i][j];
                    neighbours = countNextNeighbours(i, j, current);
                    if (position == 'L' && neighbours == 0) {
                        isStable = false;
                        clone[i][j] = '#';
                    } else if (position == '#' && neighbours >= 5) {
                        isStable = false;
                        clone[i][j] = 'L';
                    }
                }
            }
            current = clone;
        } while (!isStable);

        int occupied = 0;

        for (char[] positions : current) {
            for (int j = 0; j < positions.length; j++) {
                if (positions[j] == '#') occupied++;
            }
        }

        return String.valueOf(occupied);
    }

    private int countImmediateNeighbours (int x, int y, char[][] current) {
        int counter = 0;
        for (int[] direction : directions) {
            if (x + direction[0] < 0 || x + direction[0] >= current.length || y + direction[1] < 0 || y + direction[1] >= current[0].length) continue;
            if (current[x + direction[0]][y + direction[1]] == '#') counter++;
        }
        return counter;
    }

    private int countNextNeighbours(int x, int y, char[][] current) {
        int counter = 0;
        for (int[] direction : directions) {
            int i = direction[0];
            int j = direction[1];
            while(true) {
                if (x + i < 0 || x + i >= current.length || y + j < 0 || y + j >= current[0].length) break;
                if (current[x + i][y + j] == 'L') break;
                if (current[x + i][y + j] == '#'){
                    counter++;
                    break;
                }
                i += direction[0];
                j += direction[1];
            }
        }
        return counter;
    }

}

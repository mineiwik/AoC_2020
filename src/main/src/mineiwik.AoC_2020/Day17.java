package mineiwik.AoC_2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/*
 * Advent of Code 2020 - Day 17 - Conway Cubes
 */
public class Day17 extends Day {

    private final String[] lines;
    private final ArrayList<ArrayList<Integer>> directions3D;
    private final ArrayList<ArrayList<Integer>> directions4D;

    Day17(String day) throws IOException {
        super(day);
        lines = input.split("\\r?\\n");

        directions3D = new ArrayList<>();
        directions4D = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    for (int w = -1; w < 2; w++) {
                        if (x == 0 && y == 0 && z == 0 && w == 0) continue;
                        directions4D.add(new ArrayList<>(Arrays.asList(x, y, z, w)));
                    }
                    if (x == 0 && y == 0 && z == 0) continue;
                    directions3D.add(new ArrayList<>(Arrays.asList(x, y, z)));
                }
            }
        }
    }

    public String firstStar() {
        ArrayList<ArrayList<ArrayList<Boolean>>> grid;

        grid = new ArrayList<>();
        grid.add(new ArrayList<>());
        int i = 0;
        for (String line : lines) {
            grid.get(0).add(new ArrayList<>());
            for (char character : line.strip().toCharArray()) {
                if (character == '#') grid.get(0).get(i).add(true);
                else grid.get(0).get(i).add(false);
            }
            i++;
        }

        ArrayList<ArrayList<ArrayList<Boolean>>> clone;

        int cycle = 0;

        while (cycle < 6) {
            clone = new ArrayList<>();

            createEmptyCube(grid);

            for (ArrayList<ArrayList<Boolean>> plane : grid) {
                clone.add(plane.stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new)));
            }

            for (int z = 0; z < grid.size(); z++) {
                for (int y = 0; y < grid.get(z).size(); y++) {
                    for (int x = 0; x < grid.get(z).get(y).size(); x++) {
                        boolean cube = grid.get(z).get(y).get(x);
                        int activeNeighbours = count3DActiveNeighbours(x, y, z, grid);
                        if (cube && activeNeighbours != 2 && activeNeighbours != 3) clone.get(z).get(y).set(x, false);
                        else if (!cube && activeNeighbours == 3) clone.get(z).get(y).set(x, true);
                    }
                }
            }

            grid = clone;
            cycle++;
        }

        int sum = 0;

        for (ArrayList<ArrayList<Boolean>> plane : grid) {
            for (ArrayList<Boolean> row : plane) {
                for (Boolean position : row) {
                    if (position) sum++;
                }
            }
        }

        return String.valueOf(sum);
    }

    public String secondStar() {
        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> grid;

        grid = new ArrayList<>();
        grid.add(new ArrayList<>());
        grid.get(0).add(new ArrayList<>());
        int i = 0;
        for (String line : lines) {
            grid.get(0).get(0).add(new ArrayList<>());
            for (char character : line.strip().toCharArray()) {
                if (character == '#') grid.get(0).get(0).get(i).add(true);
                else grid.get(0).get(0).get(i).add(false);
            }
            i++;
        }

        ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> clone;

        int cycle = 0;

        while (cycle < 6) {
            clone = new ArrayList<>();

            for (ArrayList<ArrayList<ArrayList<Boolean>>> cube : grid) {
                createEmptyCube(cube);
            }
            grid.add(0, new ArrayList<>());
            grid.add(new ArrayList<>());

            for (int plane = 0; plane < grid.get(1).size(); plane++) {
                grid.get(0).add(new ArrayList<>());
                grid.get(grid.size() - 1).add(new ArrayList<>());
                for (int row = 0; row < grid.get(1).get(0).size(); row++) {
                    grid.get(0).get(plane).add(new ArrayList<>());
                    grid.get(grid.size() - 1).get(plane).add(new ArrayList<>());
                    for (int col = 0; col < grid.get(1).get(0).get(0).size(); col++) {
                        grid.get(0).get(plane).get(row).add(false);
                        grid.get(grid.size() - 1).get(plane).get(row).add(false);
                    }
                }
            }

            int j = 0;
            for (ArrayList<ArrayList<ArrayList<Boolean>>> cube : grid) {
                clone.add(new ArrayList<>());
                for (ArrayList<ArrayList<Boolean>> plane : cube) {
                    clone.get(j).add(plane.stream().map(ArrayList::new).collect(Collectors.toCollection(ArrayList::new)));
                }
                j++;
            }

            for (int w = 0; w < grid.size(); w++) {
                for (int z = 0; z < grid.get(w).size(); z++) {
                    for (int y = 0; y < grid.get(w).get(z).size(); y++) {
                        for (int x = 0; x < grid.get(w).get(z).get(y).size(); x++) {
                            boolean cube = grid.get(w).get(z).get(y).get(x);
                            int activeNeighbours = count4DActiveNeighbours(x, y, z, w, grid);
                            if (cube && activeNeighbours != 2 && activeNeighbours != 3)
                                clone.get(w).get(z).get(y).set(x, false);
                            else if (!cube && activeNeighbours == 3) clone.get(w).get(z).get(y).set(x, true);
                        }
                    }
                }
            }

            grid = clone;
            cycle++;
        }

        int sum = 0;

        for (ArrayList<ArrayList<ArrayList<Boolean>>> cube : grid) {
            for (ArrayList<ArrayList<Boolean>> plane : cube) {
                for (ArrayList<Boolean> row : plane) {
                    for (Boolean position : row) {
                        if (position) sum++;
                    }
                }
            }
        }


        return String.valueOf(sum);
    }

    private void createEmptyCube(ArrayList<ArrayList<ArrayList<Boolean>>> cube) {
        for (ArrayList<ArrayList<Boolean>> plane : cube) {
            for (ArrayList<Boolean> row : plane) {
                row.add(0, false);
                row.add(false);
            }
            plane.add(0, new ArrayList<>(Arrays.asList(new Boolean[plane.get(0).size()])));
            plane.add(new ArrayList<>(Arrays.asList(new Boolean[plane.get(0).size()])));
            Collections.fill(plane.get(0), Boolean.FALSE);
            Collections.fill(plane.get(plane.size() - 1), Boolean.FALSE);
        }
        cube.add(0, new ArrayList<>());
        cube.add(new ArrayList<>());

        for (int row = 0; row < cube.get(1).size(); row++) {
            cube.get(0).add(new ArrayList<>());
            cube.get(cube.size() - 1).add(new ArrayList<>());
            for (int col = 0; col < cube.get(1).get(0).size(); col++) {
                cube.get(0).get(row).add(false);
                cube.get(cube.size() - 1).get(row).add(false);
            }
        }
    }

    private int count3DActiveNeighbours (int x, int y, int z, ArrayList<ArrayList<ArrayList<Boolean>>> current) {
        int counter = 0;
        for (ArrayList<Integer> direction : directions3D) {
            if (z + direction.get(2) < 0
                    || z + direction.get(2) >= current.size()
                    || y + direction.get(1) < 0
                    || y + direction.get(1) >= current.get(z).size()
                    || x + direction.get(0) < 0
                    || x + direction.get(0) >= current.get(z).get(y).size()) continue;
            if (current.get(z + direction.get(2)).get(y + direction.get(1)).get(x + direction.get(0))) counter++;
        }
        return counter;
    }

    private int count4DActiveNeighbours (int x, int y, int z, int w, ArrayList<ArrayList<ArrayList<ArrayList<Boolean>>>> current) {
        int counter = 0;
        for (ArrayList<Integer> direction : directions4D) {
            if (w + direction.get(3) < 0
                    || w + direction.get(3) >= current.size()
                    || z + direction.get(2) < 0
                    || z + direction.get(2) >= current.get(w).size()
                    || y + direction.get(1) < 0
                    || y + direction.get(1) >= current.get(w).get(z).size()
                    || x + direction.get(0) < 0
                    || x + direction.get(0) >= current.get(w).get(z).get(y).size()) continue;
            if (current.get(w + direction.get(3)).get(z + direction.get(2)).get(y + direction.get(1)).get(x + direction.get(0))) counter++;
        }
        return counter;
    }

}

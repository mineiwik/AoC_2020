package mineiwik.AoC_2020;

import java.io.IOException;

/*
 * Advent of Code 2020 - Day 12 - Rain Risk
 */
public class Day12 extends Day {

    private final String[] instructions;

    Day12(String day) throws IOException {
        super(day);
        instructions = input.split("\\r?\\n");
    }

    public String firstStar() {
        Ship ship = new Ship();
        for (String instruction : instructions) {
            ship.moveCommand(instruction.charAt(0), Integer.parseInt(instruction.substring(1)));
        }
        return String.valueOf(ship.distance());
    }

    public String secondStar() {
        Ship ship = new Ship();
        Ship waypoint = new Ship(10, 1);
        for (String instruction : instructions) {
            ship.navigate(instruction.charAt(0), Integer.parseInt(instruction.substring(1)), waypoint);
        }
        return String.valueOf(ship.distance());
    }

    static class Ship {
        int x, y, directionIndex;
        private final char[] directions = {'N', 'E', 'S', 'W'};

        Ship () {
            x = y = 0;
            directionIndex = 1;
        }

        Ship (int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distance() {
            return Math.abs(x) + Math.abs(y);
        }

        public void navigate(char command, int units, Ship waypoint) {
            switch (command) {
                case 'F':
                    x += units * waypoint.x;
                    y += units * waypoint.y;
                    break;
                case 'L':
                    rotateWaypoint(units, waypoint);
                    break;
                case 'R':
                    rotateWaypoint(-units, waypoint);
                    break;
                case 'N':
                    waypoint.move(0, units);
                    break;
                case 'E':
                    waypoint.move(units, 0);
                    break;
                case 'S':
                    waypoint.move(0, -units);
                    break;
                case 'W':
                    waypoint.move(-units, 0);
                    break;
            }
        }

        public void moveCommand(char command, int units) {
            switch (command) {
                case 'F':
                    moveCommand(directions[directionIndex], units);
                    break;
                case 'L':
                    changeDirection(-((units / 90) % 4));
                    break;
                case 'R':
                    changeDirection((units / 90) % 4);
                    break;
                case 'N':
                    move(0, units);
                    break;
                case 'E':
                    move(units, 0);
                    break;
                case 'S':
                    move(0, -units);
                    break;
                case 'W':
                    move(-units, 0);
                    break;
            }
        }

        /**
         * Move this ship x units in x-direction and y-units in y-direction
         *
         * @param x units in x-direction
         * @param y units in y-direction
         */
        private void move(int x, int y) {
            this.x += x;
            this.y += y;
        }

        /**
         * Change direction in which this ship is facing
         *
         * @param degrees number of degrees to turn this ship (positive values mean to turn clockwise, negative values mean to turn counterclockwise)
         *                value of 1 turns the ship by 90 degrees clockwise
         */
        private void changeDirection(int degrees) {
            directionIndex = (directionIndex + directions.length + degrees) % directions.length;
        }

        /**
         * Rotates the waypoint around the ship by the given value
         *
         * @param degrees number of degrees to rotate this waypoint (positive values mean to turn clockwise, negative values mean to turn counterclockwise)
         * @param waypoint object to rotate
         */
        private void rotateWaypoint(int degrees, Ship waypoint) {
            int x = waypoint.x;
            int y = waypoint.y;
            final long cos = Math.round(Math.cos(Math.toRadians(degrees)));
            final long sin = Math.round(Math.sin(Math.toRadians(degrees)));
            waypoint.x = (int) (x * cos - y * sin);
            waypoint.y = (int) (x * sin + y * cos);

        }
    }


}

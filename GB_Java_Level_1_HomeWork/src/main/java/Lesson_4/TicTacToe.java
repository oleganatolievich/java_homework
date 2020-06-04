package Lesson_4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static int mapSize = 3;
    private static int dotsToWin = 3;
    private static final char DOT_EMPTY = '•';
    private static char[][] map;
    private static Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();
    private static Player playerUser = new Player('O', false, "Игрок");
    private static Player playerAI = new Player('X', true, "AI");

    public static void main(String[] args) {
        System.out.println("Введи размер поля (от 3 до 9): ");
        mapSize = sc.nextInt();
        if (mapSize < 3 || mapSize > 9) {
            System.out.println("Ты должен был бороться со злом, а не примкнуть к нему!");
            sc.close();
            System.exit(1);
        }
        if (mapSize > 3) {
            System.out.println("Введи количество знаков для победы (от 3 до 9): ");
            dotsToWin = sc.nextInt();
            if (dotsToWin < 3 || dotsToWin > mapSize) {
                System.out.println("Все в хакера играешь?");
                sc.close();
                System.exit(1);
            }
        }
        initMap();
        printMap();
        gameCycle();
        System.out.println("Игра закончена");
    }

    private static void gameCycle() {
        boolean gameFinished = false;
        Player[] players = {playerUser, playerAI};
        do {
            for (Player player : players) {
                player.makeTurn();
                printMap();
                if (player.checkForVictory()) {
                    System.out.printf("Победитель: %s\n", player);
                    gameFinished = true;
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    gameFinished = true;
                    break;
                }
            }
        } while (!gameFinished);
    }

    private static boolean isMapFull() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= mapSize || y < 0 || y >= mapSize) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    private static void initMap() {
        map = new char[mapSize][mapSize];
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= mapSize; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < mapSize; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < mapSize; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static class Player {
        private char sign;
        private boolean thisIsAI;
        private String name;

        public Player(char sign, boolean thisIsAI, String name) {
            this.sign = sign;
            this.thisIsAI = thisIsAI;
            this.name = name;
        }

        public char getSign() {
            return sign;
        }

        @Override
        public String toString() {
            return name;
        }

        public void makeTurn() {
            int x, y;
            if (thisIsAI) {
                Coordinates AICoodinates = getAICoordinates();
                if (AICoodinates == null) {
                    do {
                        x = rand.nextInt(mapSize);
                        y = rand.nextInt(mapSize);
                    } while (!isCellValid(x, y));
                } else
                {
                    x = AICoodinates.x;
                    y = AICoodinates.y;
                }
                System.out.println("Ход AI: x = " + (x + 1) + ", y = " + (y + 1));
                map[y][x] = sign;
            } else {
                do {
                    System.out.println("Введите координаты в формате X Y");
                    x = sc.nextInt() - 1;
                    y = sc.nextInt() - 1;
                } while (!isCellValid(x, y));
                map[y][x] = sign;
            }
        }

        public Coordinates getAICoordinates() {

            //как бы я это делал в 1С
            ArrayList<Coordinates> coords = new ArrayList<Coordinates>();

            coords.addAll(getHorizontalMoves());
            coords.addAll(getVerticalMoves());
            coords.addAll(getDiagonalMoves(true));
            coords.addAll(getDiagonalMoves(false));

            int maxRepeatsCount = 0;
            for (Coordinates coord: coords) {
                if (coord.repeatsCount > maxRepeatsCount) maxRepeatsCount = coord.repeatsCount;
            }
            if (maxRepeatsCount > 0) {
                for (Coordinates coord: coords) {
                    if (coord.repeatsCount == maxRepeatsCount) return coord;
                }
            }
            return null;
        }

        private ArrayList<Coordinates> getHorizontalMoves() {
            int repeatsCount = 0;
            char prevChar;
            ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
            char playerSign = playerUser.getSign();

            for (int y = 0; y < mapSize; y++) {
                repeatsCount = 0;
                prevChar = playerSign;
                for (int x = 0; x < mapSize; x++) {
                    if (map[y][x] == playerSign && prevChar == playerSign) repeatsCount++;
                    else {
                        if (repeatsCount > 1 && map[y][x] == DOT_EMPTY) {
                            coords.add(new Coordinates(x, y, repeatsCount));
                        }
                        repeatsCount = 0;
                    }
                    prevChar = map[y][x];
                }
            }
            return coords;
        }

        private ArrayList<Coordinates> getVerticalMoves() {
            int repeatsCount = 0;
            char prevChar;
            ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
            char playerSign = playerUser.getSign();

            for (int x = 0; x < mapSize; x++) {
                repeatsCount = 0;
                prevChar = playerSign;
                for (int y = 0; y < mapSize; y++) {
                    if (map[y][x] == playerSign && prevChar == playerSign) repeatsCount++;
                    else {
                        if (repeatsCount > 1 && map[y][x] == DOT_EMPTY) {
                            coords.add(new Coordinates(x, y, repeatsCount));
                        }
                        repeatsCount = 0;
                    }
                    prevChar = map[y][x];
                }
            }
            return coords;
        }

        private ArrayList<Coordinates> getDiagonalMoves(boolean leftToRight) {
            int repeatsCount = 0;
            int x, y;
            char prevChar;
            ArrayList<Coordinates> coords = new ArrayList<Coordinates>();
            char playerSign = playerUser.getSign();

            for (int i = 1; i <= (mapSize + mapSize - 1); i++) {
                int columnIndex = Math.max(0, i - mapSize);
                int count = minOf3Numbers(i, (mapSize - columnIndex), mapSize);
                if (count < dotsToWin) continue;
                repeatsCount = 0;
                prevChar = playerSign;
                for (int j = 0; j < count; j++) {
                    y = Math.min(mapSize, i) - j - 1;
                    if (leftToRight) x = columnIndex + j;
                    else x = mapSize - columnIndex - j - 1;
                    if (map[y][x] == playerSign && prevChar == playerSign) repeatsCount++;
                    else {
                        if (repeatsCount > 1 && map[y][x] == DOT_EMPTY) {
                            coords.add(new Coordinates(x, y, repeatsCount));
                        }
                        repeatsCount = 0;
                    }
                    prevChar = map[y][x];
                }
            }
            return coords;
        }

        public boolean checkForVictory() {
            boolean victoryFound = false;
            if (isHorizontalVictoryFound()) victoryFound = true;
            else if (isVerticalVictoryFound()) victoryFound = true;
            else if (isDiagonalVictoryFound(true)) victoryFound = true;
            else if (isDiagonalVictoryFound(false)) victoryFound = true;

            return victoryFound;
        }

        private boolean isHorizontalVictoryFound() {
            int repeatsCount = 0;
            char prevChar;
            boolean victoryFound = false;
            searching:
            for (int y = 0; y < mapSize; y++) {
                repeatsCount = 0;
                prevChar = sign;
                for (int x = 0; x < mapSize; x++) {
                    if (map[y][x] == sign && prevChar == sign) repeatsCount++;
                    else repeatsCount = 0;
                    if (repeatsCount == dotsToWin) {
                        victoryFound = true;
                        break searching;
                    }
                    prevChar = map[y][x];
                }
            }
            return victoryFound;
        }

        private boolean isVerticalVictoryFound() {
            int repeatsCount = 0;
            char prevChar;
            boolean victoryFound = false;
            searching:
            for (int x = 0; x < mapSize; x++) {
                repeatsCount = 0;
                prevChar = sign;
                for (int y = 0; y < mapSize; y++) {
                    if (map[y][x] == sign && prevChar == sign) repeatsCount++;
                    else repeatsCount = 0;
                    if (repeatsCount == dotsToWin) {
                        victoryFound = true;
                        break searching;
                    }
                    prevChar = map[y][x];
                }
            }
            return victoryFound;
        }

        private boolean isDiagonalVictoryFound(boolean leftToRight) {
            int repeatsCount = 0;
            int x, y;
            char prevChar;
            boolean victoryFound = false;
            searching:
            for (int i = 1; i <= (mapSize + mapSize - 1); i++) {
                int columnIndex = Math.max(0, i - mapSize);
                int count = minOf3Numbers(i, (mapSize - columnIndex), mapSize);
                if (count < dotsToWin) continue;
                repeatsCount = 0;
                prevChar = sign;
                for (int j = 0; j < count; j++) {
                    y = Math.min(mapSize, i) - j - 1;
                    if (leftToRight) x = columnIndex + j;
                    else x = mapSize - columnIndex - j - 1;
                    if (map[y][x] == sign && prevChar == sign) repeatsCount++;
                    else repeatsCount = 0;
                    if (repeatsCount == dotsToWin) {
                        victoryFound = true;
                        break searching;
                    }
                    prevChar = map[y][x];
                }
            }
            return victoryFound;
        }

        private int minOf3Numbers(int a, int b, int c) {
            return Math.min(Math.min(a, b), c);
        }
    }
    private static class Coordinates {
        public int x;
        public int y;
        public int repeatsCount;

        public Coordinates(int x, int y, int repeatsCount) {
            this.x = x;
            this.y = y;
            this.repeatsCount = repeatsCount;
        }
    }
}

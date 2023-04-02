package org.example;

import java.util.Arrays;

public class Main {

    public static int size = 10;

    public static char[][] field = {
            {'Щ', '-', '-', '*', '*', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '*', '-', '*', '*', '-', '-'},
            {'-', '-', '-', '*', '-', '*', '-', '-', '-', '*'},
            {'-', '*', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '*', '-', '-', '-'},
            {'-', '-', '*', '-', '-', '*', '-', '-', '-', '-'},
            {'-', '-', '-', '*', '-', '-', '*', '*', '*', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '*', '-', '-'},
            {'-', '-', '-', '-', '-', '-', '-', '*', '-', '-'},
            {'-', '-', '-', '-', '-', '*', '*', '-', '-', '-'},
    };

    public static void main(String[] args) {
        findPath(field, 3, 8);
    }

//    public static void printField(char[][] field) {
//        for (int i = 0; i < field.length; i++) {
//            for (int j = 0; j < field[i].length; j++) {
//                System.out.print(field[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

    public static void findPath(char[][] field, int x0, int y0) {
        boolean[][] path = new boolean[size][size];
        char[][] memory = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                memory[i][j] = '?';
            }
        }
        int x = x0;
        int y = y0;
        while (x != 0 || y != 0) {
            char direction = whereFrom(field, x, y, memory);
            if (direction == 'N') {
                System.out.println("Нет такого пути :(");
                break;
            } else if (direction == 'U') {
                path[x][y] = true;
                y -= 1;
            } else if (direction == 'L') {
                path[x][y] = true;
                x -= 1;
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == x0 && j == y0) {
                    System.out.print('Ч' + " ");
                } else if (path[i][j]) {
                    System.out.print('X' + " ");
                } else {
                    System.out.print(field[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


    public static char whereFrom(char[][] field, int x, int y, char[][] memory) {
        if (memory[x][y] != '?') {
            return memory[x][y];
        }
        if (x > 0) {
            int left_x = x - 1;
            int left_y = y;
            if (left_x == 0 && left_y == 0) {
                memory[x][y] = 'L';
                return 'L';
            }
            if (field[left_x][left_y] != '*') {
                if (whereFrom(field, left_x, left_y, memory) != 'N') {
                    memory[x][y] = 'L';
                    return 'L';
                }
            }
        }
        if (y > 0) {
            int up_x = x;
            int up_y = y - 1;
            if (up_x == 0 && up_y == 0) {
                memory[x][y] = 'U';
                return 'U';
            }
            if (field[up_x][up_y] != '*') {
                if (whereFrom(field, up_x, up_y, memory) != 'N') {
                    memory[x][y] = 'U';
                    return 'U';
                }
            }
        }
        memory[x][y] = 'N';
        return 'N';
    }
}
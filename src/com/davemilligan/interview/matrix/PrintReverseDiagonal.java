package com.davemilligan.interview.matrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintReverseDiagonal {

    static List<List<Integer>> matrix = new ArrayList<>();

    static {
        List<Integer> one =     Arrays.asList(6 , 3,  1,  0);
        List<Integer> two =     Arrays.asList(10, 7,  4,  2);
        List<Integer> three =   Arrays.asList(13, 11, 8,  5);
        List<Integer> four =    Arrays.asList(15, 14, 12, 9);

        matrix.add(one);
        matrix.add(two);
        matrix.add(three);
        matrix.add(four);
    }

    public static void main(String[] args) throws IOException {
        reverseDiagonalMatrix();
    }

    public static void reverseDiagonalMatrix() {
        int n = matrix.size(), startCol = n, startRow = 0;

        while (startRow < n) {
            while (startCol >= 0) {
                int x = startRow, y = startCol--;
                while (x < n && y < n) {
                    System.out.println(matrix.get(x++).get(y++));
                }
            }
            startCol = 0;
            startRow++;
        }
    }
}

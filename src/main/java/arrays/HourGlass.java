package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HourGlass {

    static int a[][] = {{0 ,-4, -6, 0, -7, -6},
            {-1, -2, -6, -8, -3, -1},
            {-8, -4, -2, -8, -8, -6},
            {-3, -1, -2, -5, -7, -4},
            {-3, -5, -3, -6 ,-6, -6},
            {-3, -6, 0, -8, -6, -7}};

    public static void main(String[] args) {
        System.out.println(printHourglasses(a));
    }

    private static int printHourglasses(int[][]arr) {
        int startRow = 0, startCol = 0, endRow = 3, boundary = arr.length;
        List<Integer> sums = new ArrayList<>();

        while (startRow < boundary - 2) {
            while (startCol <  boundary - 2) {
                int currentSum = 0;
                for (int x = 0; x < 3; x++) {
                    boolean middleRow = (x == 1);
                    for (int y = (middleRow ? 1 : 0); y < (middleRow ? 2 : 3); y++) {
                        currentSum += (arr[x + startRow][y + startCol]);
                    }
                }
                //System.out.println(currentSum);
                sums.add(currentSum);
                startCol++;
            }
            startCol = 0;
            startRow++;
        }
        return sums.stream().max(Integer::compareTo).get();
    }
}

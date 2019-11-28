import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        List<Integer> one =     Arrays.asList(6 , 3,  1,  0);
        List<Integer> two =     Arrays.asList(10, 7,  4,  2);
        List<Integer> three =   Arrays.asList(13, 11, 8,  5);
        List<Integer> four =    Arrays.asList(15, 14, 12, 9);
        List<List<Integer>> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        reverseDiag(list);
    }

    public static void reverseDiag(List<List<Integer>> list) {
        int n = list.size(), startCol = n, startRow = 0;
        while (startRow < n) {
            while (startCol >= 0) {
                int x = startRow, y = startCol--;
                while (x < n && y < n) {
                    System.out.println(list.get(x++).get(y++));
                }
            }
            startRow++;
        }
    }
}

package com.davemilligan.interview.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayListQueries {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        try( Scanner scan = new Scanner(System.in)) {
            int lines = scan.nextInt();
            for (int line = 0; line < lines; line++) {
                int nums = scan.nextInt();
                List<Integer> list = new ArrayList<>();
                for (int num = 0; num < nums; num++) {
                    list.add(scan.nextInt());
                }
                lists.add(list);
            }

            int queries = scan.nextInt();
            for (int query = 0; query < queries; query++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                if (x > lists.size())
                    System.out.printf("%s\n", "ERROR!");
                else {
                    List<Integer> list = lists.get(x - 1);
                    if (y > list.size())
                        System.out.printf("%s\n", "ERROR!");
                    else
                        System.out.printf("%d\n", list.get(y -1));
                }
            }
        }
    }
}

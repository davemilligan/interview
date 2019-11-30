package com.davemilligan.interview.hackerrank.arrays;

import java.util.Arrays;
import java.util.List;

public class PairClosestToZero {

//    static int output, a [] = {1, 4, -5, 3, -2, 10, -6, 20};
    static int output, a [] = {5, 8};
//    static int output, a [] = {-5, 5, 8};
    public static void main(String[] args) {
//        Arrays.sort(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]);
//        }

        sortedArray();
        twoLoops();
    }

    private static void twoLoops() {
        long startTime = System.nanoTime();
        //Output: Sum close to zero in the given array is : 1
        output = a.length > 1 ? a[0] + a[1] : a.length == 1 ? a[0] : 0;
        for (int i = 0; i < a.length; i++) {
            for (int x = i +1; x < a.length - i; x++) {

                if (Math.abs(a[i] + a[x]) < Math.abs(output)) {

                    output = a[i] + a[x];
                }

            }
        }
        long endTime = System.nanoTime();
        System.out.println("twoLoops Total execution time: " + (endTime-startTime) + "ns");
        System.out.println(output);
    }

    //  More efficient in the comparison but the sort kills it.
    private static void sortedArray() {
        long startTime = System.nanoTime();
        Arrays.sort(a);
        Arrays.sort(a);
        int i = 0, j = a.length -1;
        output = a[i] + a[j];
        while (i < j) {
            int tmp = a[i] + a[j];
            if (tmp > 0)
                j--;
            else if (tmp < 0)
                i++;
            else {
                long endTime = System.nanoTime();
                System.out.println("sortedArray Total execution time: " + (endTime-startTime) + "ns");
                System.out.println(0);
                return;
            }
            output = tmp;
        }
        long endTime = System.nanoTime();
        System.out.println("sortedArray Total execution time: " + (endTime-startTime) + "ns");
        System.out.println(output);
    }
}

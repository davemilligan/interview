package com.davemilligan.interview.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MaxSubArraySum {

    public static void main(String[] args) {
        int[] arr = {9, 6,-2, 4, 8};
        int len = 4;
        System.out.println(maxSubArray(arr, len));
    }

    /**
     * Find biggest sub array of length n.
     * @param arr
     * @return
     */
    static int maxSubArray(int[] arr, int n) {
        assert(arr.length > 2);

        int[] totals = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (i == 0)
                totals[i] = arr[i];
            else if (i < n)
                totals[i] = arr[i] + totals[i-1];
            else
                totals[i] = arr[i] + totals[i-1] - arr[i-n];
        }
        return IntStream.of(totals).max().getAsInt();
    }
}

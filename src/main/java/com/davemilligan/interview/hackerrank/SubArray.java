package com.davemilligan.interview.hackerrank;

public class SubArray {
    public static void main(String[] args) {
        int[] arr = new int[]{1,-2, 4, -5, 1};
        System.out.println(countSubarrays(arr));
    }

    public static int countSubarrays(int[]arr) {
        int negArrs = 0;
        int iTotals = 0;
        for (int i = 0; i < arr.length; i++) {
            iTotals = arr[i];
            if (iTotals < 0) negArrs++;
            for (int j = i+1; j < arr.length; j++) {
                iTotals += arr[j];
                if (iTotals < 0) negArrs++;
            }
        }

        return negArrs;
    }
}

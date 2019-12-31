package com.davemilligan.interview.hackerrank;

import java.util.*;
import java.util.stream.IntStream;

public class MaxArraySum {

    static Map<Integer, Integer> sumMap = new HashMap<>();
    public static void main(String[] args) {
        int[] arr = {3,2,4,6,5};
        System.out.println(maxSubsetSumWithDP(arr));
    }

    static int maxSubsetSum(int[] arr) {
        int[] totals = Arrays.copyOf(arr, arr.length);
        for (int i = 0; i < arr.length ; i++) {
            int newTotal = arr[i];
            for (int jump = 2; jump < arr.length; jump++) {
                //System.out.println("Checking with jump of:" + jump);
                for (int next = i + jump; next < arr.length; next += jump) {
                    //System.out.printf("Checking sum of %d + %d \n", newTotal, arr[next]);
                    newTotal = newTotal + arr[next];
                }
                if (newTotal > totals[i]) {
                    totals[i] = newTotal;
                    //printArr(totals);
                }
                newTotal = arr[i];

            }

            //printArr(totals);
        }

        //printArr(totals);
        return IntStream.of(totals).max().getAsInt();
    }

    /**
     * We are going to traverse the array once, keeping tally of totals
     * as we go along.  Note we'll only ever want the max value we can find, as long
     * as we get this we can forget everything else.
     *
     * Seems to be that we replace a smaller total before with the previous bigger number.
     * @param arr
     * @return
     */
    static int maxSubsetSumWithDP(int[] arr) {
        assert(arr.length > 1);

        int[] totals = new int[arr.length];
        totals[0]=arr[0];
        totals[1]=Math.max(totals[0],arr[1]);
        for (int i = 2; i < arr.length; i++) {
            //  The last calculated number is the biggest we know so far.
            int lastCalculated = totals[i-1];
            int currentNumber = arr[i];
            int maxOfBiggestWeKnowAndCurrent = Math.max(lastCalculated, currentNumber);
            int lastButOneCalculated = totals[i-2];
            int sumOfCurrentInOriginalArrAndLastButOneCalculated =  lastButOneCalculated + currentNumber;
            // Now we have the max total found so far.
            totals[i] = Math.max(maxOfBiggestWeKnowAndCurrent, sumOfCurrentInOriginalArrAndLastButOneCalculated);
        }
        return totals[arr.length-1];
    }

    static void printArr(int[] arr) {
        IntStream.of(arr).forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

}

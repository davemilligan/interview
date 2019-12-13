package com.davemilligan.interview.hackerrank.arrays;

public class DigitsCount {

    /**
     * What might cause this to go into an infinite loop.
     * Long.max_value
     */
    public static int digitsCount(long value) {
        int length = 0;
        long temp = 1;
        while (temp <= value) {
            length++;
            temp+= 10;
        }
        return length;
    }
}

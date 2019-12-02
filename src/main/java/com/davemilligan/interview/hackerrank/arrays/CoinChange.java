package com.davemilligan.interview.hackerrank.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoinChange {

    public static void main(String[] args) {
        int n = 10;
        List<Long> list = Arrays.asList(2l,5l,3l,6l);
        System.out.println(getWays(n, list));
    }

    /**
     * Cacluate how many ways chnage can be generated given an infinite number of coins.
     * @param n A value to make change for.
     * @param coins A list of coin denominations
     * @return
     */
    public static long getWays(int n, List<Long> coins) {
        Collections.sort(coins);
        System.out.println(coins);
        long[] totals = new long[n + 1];
        totals[0] = 1;
        for (Long coin: coins) {
            for (int i = 1; i < totals.length; i++) {
                if (coin > i)
                    continue;
                int remainder = i - coin.intValue();
                totals[i] += totals[remainder];
            }
            IntStream.rangeClosed(0, n).forEach(i -> System.out.print(i + ":" + totals[i] + "--"));
            System.out.println();
        }
        IntStream.rangeClosed(0, n).forEach(i -> System.out.println(i + ":" + totals[i]));
        return totals[n];
    }
}

package com.davemilligan.interview.hackerrank.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SparseArrays {

    public static void main(String[] args) {
        String[]strings = {"aba", "baba", "aba", "xzxb"}, queries = {"aba", "xzxb", "ab"};
        System.out.println(matchingStrings(strings, queries));
    }

    /**
     * Finding occurances of queries in strings, thpough what this has to do with sparse arrays; i do not know.
     * @param strings
     * @param queries
     * @return
     */
    static int[] matchingStrings(String[] strings, String[] queries) {
        List<Integer> result;
        result = Arrays.stream(queries).map(q -> {
            return Arrays.stream(strings).filter(s -> {
                return s.equals(q);
            }).collect(Collectors.toList()).size();
        }).collect(Collectors.toList());
        System.out.println(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}

package com.davemilligan.interview.hackerrank.arrays;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This needs to find the shortest substring in a string
 * containing all of the unique characters in the string.
 */
public class ShortestSubstring {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(shortestSubstring("bcaacbc"));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("shortestSubstring Duration:" + duration);

        startTime = System.nanoTime();
        System.out.println(shortestSubstring("bcaacbc"));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("shortestSubstringPerformance Duration:" + duration);
    }

    public static int shortestSubstring(String s) {
        // Write your code here
        Set<Character> unique = new HashSet<>();
        for (char c: s.toCharArray()) {
            unique.add(c);
        }
        int slen = s.length();
        int uniqueSize = unique.size();
        int smallest = slen;
        for (int i = 0; i <= slen; i++) {
            for (int j = i+1; j <= slen; j++) {
                String target = s.substring(i, j);
                int len = j - 1;
                if (len >= uniqueSize) {
                    Set<Character> targetUnique = new HashSet<>();
                    for (char c : target.toCharArray()) {
                        targetUnique.add(c);
                    }
                    if (targetUnique.containsAll(unique) && len < smallest) {
                        smallest = len;
                    }
                }
            }
        }
        return smallest;
    }

    public static int shortestSubstringPerformance(String s) {
        // Write your code here
        Set<String> unique = new HashSet<String>(Arrays.asList(s.split("")));
        System.out.println(unique);
        int slen = s.length();
        int uniqueSize = unique.size();
        int smallest = slen;
        for (int i = 0; i <= slen; i++) {
            for (int j = i+1; j <= slen; j++) {
                List<String> targetUnique = Arrays.asList(s.substring(i, j).split(""));
                int len = j - 1;
                //  the substring needs to be longer than unique chars, and shorter than existing smallest.
                if (len >= uniqueSize && len < smallest) {
                    if (targetUnique.containsAll(unique)) {
                        smallest = len;
                    }
                }
            }
        }
        return smallest;
    }
}

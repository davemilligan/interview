package com.davemilligan.interview.hackerrank;

import java.util.*;

/**
 * This needs to find the shortest substring in a string
 * containing all of the unique characters in the string.
 */
public class SharedSubstring {

    public static boolean sharedSubstring(String s1, String s2) {
        // Write your code here
        boolean result = false;
        Map<Integer, Boolean> map1 = new HashMap<>();
        s1.chars().forEach(c -> {
            map1.put(c, true);
        });

        return s2.chars().anyMatch(c -> {
            return map1.containsKey(c);
        });
    }
}

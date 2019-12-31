package com.davemilligan.interview.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class IsAnagram {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        System.out.println(isAnagram("AAbb", "AAbb"));
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("isAnagram Duration:" + duration);

        startTime = System.nanoTime();
        System.out.println(isAnagramBetter("AAbb", "AAbb"));
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        System.out.println("isAnagramBetter Duration:" + duration);
    }

    public static boolean isAnagram(String a, String b) {
        final java.util.Map<String, Integer> aMap = new java.util.HashMap<>();
        java.util.Arrays.asList(a.split("")).forEach(c -> {
            if (aMap.containsKey(c.toLowerCase()))
                aMap.put(c.toLowerCase(), aMap.get(c.toLowerCase()) + 1);
            else
                aMap.put(c.toLowerCase(), 1);
        });
        final java.util.Map<String, Integer> bMap = new java.util.HashMap<>();
        java.util.Arrays.asList(b.split("")).forEach(c -> {
            if (bMap.containsKey(c.toLowerCase()))
                bMap.put(c.toLowerCase(), bMap.get(c.toLowerCase()) + 1);
            else
                bMap.put(c.toLowerCase(), 1);
        });
        return (aMap.equals(bMap));
    }

    public static boolean isAnagramBetter(String a, String b) {
        final java.util.Map<String, Integer> aMap = new java.util.HashMap<>();
        for (String c : a.toLowerCase().split("")) {
            Integer has = aMap.get(c);
            aMap.put(c, (has != null) ? has + 1: 1);
        }
        final java.util.Map<String, Integer> bMap = new java.util.HashMap<>();
        for (String c : b.toLowerCase().split("")) {
            Integer has = bMap.get(c);
            bMap.put(c, (has != null) ? has + 1: 1);
        }
        return (aMap.equals(bMap));
    }

    static int sherlockAndAnagrams(String s) {
        int count = 0;
        int slen = s.length();
        String reversed = new StringBuilder(s).reverse().toString();
        Map<String, Integer> map1 = new HashMap<>();
        //  Get a count of the occurrences of each substring.
        for (int i = 0; i <= slen; i++) {
            for (int j = i+1; j <= slen; j++) {
                String ss = s.substring(i, j);
                int currentCount = map1.getOrDefault(ss, 0);
                map1.put(ss, ++currentCount);
            }

        }

        for (int i = 0; i <= slen; i++) {
            for (int j = i+1; j <= slen; j++) {
                String ss = reversed.substring(i, j);
                int existing = map1.getOrDefault(ss, 0);
                if (existing > 0)
                    existing--;
                count += existing;
            }
        }
        return count;
    }

    /**
     * Count removals required to make anagram.
     * @param a
     * @param b
     * @return
     */
    static int makeAnagram(String a, String b) {
        final java.util.Map<String, Integer> aMap = new java.util.HashMap<>();
        for (String c : a.split("")) {
            aMap.put(c, aMap.getOrDefault(c, 0) + 1);
        }
        final java.util.Map<String, Integer> bMap = new java.util.HashMap<>();
        for (String c : b.split("")) {
            bMap.put(c, bMap.getOrDefault(c, 0) + 1);
        }
        return (aMap.equals(bMap) ? 0 : removeLetters(aMap, bMap));

    }

    static int removeLetters(Map<String,Integer> aMap, Map<String,Integer> bMap) {
        int difference = countDifferences(aMap, bMap);
        difference += countDifferences(bMap, aMap);
        return difference;
    }

    static int countDifferences(Map<String,Integer> aMap, Map<String,Integer> bMap) {
        int difference = 0;
        for (String chr: aMap.keySet()) {
            int count = aMap.get(chr);
            int bMapCount = bMap.getOrDefault(chr, 0);
            if (count > bMapCount)
                difference += (count - bMapCount);
        }
        return difference;
    }
}

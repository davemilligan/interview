package com.davemilligan.interview.hackerrank;

import java.util.ArrayList;
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
}

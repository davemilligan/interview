package com.davemilligan.interview.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class SherlockAndValidString {
    public static void main(String[] args) {
        String s = "aabbc";
        System.out.println(isValid(s));
    }

    static String isValid(String s) {

        final java.util.Map<String, Integer> characterMap = new java.util.HashMap<>();
        for (String c : s.split("")) {
            characterMap.put(c, characterMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> distinctNumbers = characterMap.values().stream().distinct().collect(Collectors.toList());

        if (distinctNumbers.size() == 1) {
            return "YES";
        }

        if (distinctNumbers.size() > 2) {
            return "NO";
        }
        Collections.sort(distinctNumbers);
        List<Integer> minCount = characterMap.values().stream().filter(v -> v == distinctNumbers.get(0)).collect(Collectors.toList());
        List<Integer> maxCount = characterMap.values().stream().filter(v -> v == distinctNumbers.get(1)).collect(Collectors.toList());

        //  So minCount contains the only different value and if has only one entry then it can be removed.
        if ((minCount.size() == 1) && minCount.get(0) == 1) {
            return "YES";
        }

        if ((minCount.size() > 1 && maxCount.size() > 1) || (minCount.size() < maxCount.size())) {
            return "NO";
        }
        //  get the first value in minCount
        int targetCount = minCount.get(0);
        //  So we have a single count in max count of the only value that can be reduced by one.
        if (targetCount == maxCount.get(0) - 1)
            return "YES";

        return "NO";
    }
}

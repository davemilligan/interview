package com.davemilligan.interview.hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SpecialStringAgain {
    public static void main(String[] args) {
        String s = "abcbaba";
        System.out.println(substrCount(s.length(), s));

    }

    static long substrCount(int n, String s) {
        int count = n;

        for (int i = 1; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String ss = s.substring(i - 1, j);

                Set<Character> unique = new HashSet<>();
                for (char c: ss.toCharArray()) {
                    unique.add(c);
                }

                if (unique.size() == 1) {
                    count++;
                    continue;
                }

                if (unique.size() > 2)
                    continue;




                //System.out.println("Checking:" + ss);
                if(isSpecialString(ss)) {
                    count++;
                    //System.out.println(ss);
                }
            }
        }
        return count;
    }

    static boolean isSpecialString(String s) {
        if (s.length() % 2 == 1) {
            int middleChar = (s.length() / 2);
            return s.charAt(middleChar) != s.charAt(0);
        }
        return false;
    }

}

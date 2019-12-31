package com.davemilligan.interview.hackerrank;

import java.util.*;
import java.util.stream.Collectors;

public class Triplets {

    static Map<Integer, Long> triplets = new HashMap<>();
    public static void main(String[] args) {
    }

    static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> position2 = new HashMap<>();
        Map<Long, Long> position3 = new HashMap<>();
        int count = 0;
        for (long a: arr) {
            //  We have stored potential matches, if its in position3 then we have encountered
            //  all values leading to that value before, and the count indicates how many matches.
            count += position3.getOrDefault(a, 0L);

            //  Check position2,
            //  if its there we will then add the expected value in position3 and increment,
            //  thus when we get encounter the value in position3 we have a count of how many matches there are.
            if (position2.containsKey(a))
                position3.put(a*r, position3.getOrDefault(a*r, 0L) + position2.get(a));

            //  Now increment position2
            position2.put(a*r, position2.getOrDefault(a*r, 0L) + 1);
        }
        return count;
    }
}

package com.davemilligan.interview.hackerrank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class UniquePairs {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] pair_left = new String[t];
        String[] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        Set<Pair> pairs = new HashSet<>();

        for (int i = 0; i < t; i++) {

            //  or simply use a Set<String>
//            pairs.add(pair_left[i] + "_" + pair_right[i];

            Pair pair = new Pair(pair_left[i], pair_right[i]);
            pairs.add(pair);
            System.out.println(pairs.size());
        }
    }

    private static class Pair {
        String a, b;

        Pair(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(a, pair.a) &&
                    Objects.equals(b, pair.b);
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b);
        }

    }
}

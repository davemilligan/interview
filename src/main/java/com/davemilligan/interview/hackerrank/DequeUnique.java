package com.davemilligan.interview.hackerrank;

import java.util.*;

public class DequeUnique {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int maxUnique = 0;
        int added = 0;
        boolean thresholdReached = false;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            int num = in.nextInt();
            deque.addFirst(num);
            set.add(num);
            thresholdReached |= (deque.size() == m);
            if (thresholdReached) {
                int count = set.size();
                if (count > maxUnique)
                    maxUnique = count;
                if (maxUnique == m)
                    break;
                deque.pollLast();
                set.clear();
                set.addAll(deque);
            }

        }
        System.out.println(maxUnique);
    }
}

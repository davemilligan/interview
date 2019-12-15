package com.davemilligan.interview.hackerrank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeftRotation {

    static int a[] = {1,2,3,4,5};
    static int d = 4;
    public static void main(String[] args) {

        //using just array
//        while (d > 0) {
//            int i = 0;
//            int tmp = a[i];
//            while (i < a.length - 1) {
//                a[i] = a[i+1];
//                i++;
//            }
//            a[a.length - 1] = tmp;
//            d--;
//        }
//        IntStream.of(a).forEach(i -> System.out.print(i + " "));

        //  Quicker

        LinkedList<Integer> list = new LinkedList<Integer>(IntStream.of(a).boxed().collect(Collectors.toList()));
        for (int i = 0; i < 4; i++) {
            list.offer(list.poll());
        }
        System.out.println(list);
    }
}

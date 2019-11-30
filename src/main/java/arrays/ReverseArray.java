package arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReverseArray {

    static int a [] = {1,2,3,4,5,6,7,8,9};

    public static void main(String[] args) {
        Arrays.stream(reverseArray(a)).forEach(System.out::print);
        System.out.println();
        Arrays.stream(collectionsReverse(a)).forEach(System.out::print);
    }

    private static int[] reverseArray(int[] a) {
        Arrays.stream(a).forEach(System.out::print);
        System.out.println();
        return IntStream.range(0, a.length).map(i -> a[a.length - i - 1]).toArray();
    }

    private static int[] collectionsReverse(int[] a) {
        Arrays.stream(a).forEach(System.out::print);
        System.out.println();
        List<Integer> list = IntStream.of(a).boxed().collect(Collectors.toList());
        Collections.reverse(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

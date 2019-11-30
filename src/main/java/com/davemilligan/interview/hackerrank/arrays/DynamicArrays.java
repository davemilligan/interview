package com.davemilligan.interview.hackerrank.arrays;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

interface Query {
    int doIt(int x, int lastAns);
}

public class DynamicArrays {

    List<Integer> result;

    public static void main(String[] args) throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("input.txt");
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);


        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = dynamicArray(n, queries);

        System.out.println(result);
    }



    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        Integer lastAnswer = 0;
        List<Integer> result = new ArrayList<>();
        Query q1 = (int x, int lastAns) -> (x^lastAns) % n;

        List<List<Integer>> seqList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            seqList.add(new ArrayList<>());
        //seqList.add(new ArrayList<>());

        for (List<Integer> l : queries) {
            int q = l.get(0), x = l.get(1), y = l.get(2);
            int idx = q1.doIt(x, lastAnswer);
            if (q == 1) {
                seqList.get(idx).add(y);
            } else {
                int size = seqList.get(idx).size();
                lastAnswer = seqList.get(idx).get(y % size);
                result.add(lastAnswer);
            }
        }
        return result;
    }



}

package com.davemilligan.interview.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MthElementFromEnd {

    public static void main(String[] args) throws IOException {
        try (BufferedReader sc = new BufferedReader(new InputStreamReader(System.in))) {
            int m = Integer.parseInt(sc.readLine());
            String[] a = sc.readLine().split("\\s+");
            int len = a.length;
            if (m >= len)
                System.out.println("NIL");
            else
                System.out.println(a[len - m]);
        }
    }
}

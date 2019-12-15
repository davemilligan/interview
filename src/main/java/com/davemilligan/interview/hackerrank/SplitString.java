package com.davemilligan.interview.hackerrank;

import java.util.Scanner;

public class SplitString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        String[] arr = s.split("[!,?.*_'@\\ ]+");
        for (String str: arr)
            System.out.printf("%s\n", str);
        scan.close();
    }

}

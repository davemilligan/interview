package com.davemilligan.interview.hackerrank;

public class IsPalindrome {
    public static void main(String[] args) {
        String madam = "madam";
        StringBuilder sb = new StringBuilder(madam);
        System.out.println(sb.reverse().toString().equals(madam));

    }
}

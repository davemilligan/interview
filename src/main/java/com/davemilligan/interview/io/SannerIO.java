package com.davemilligan.interview.io;

import java.util.*;

public class SannerIO {
    public static void main(String []argh){
        int a = 5;
        int b = 3;
        int n = 5;
        List<Integer> items = new ArrayList<>();
        int total = (a + ((int)Math.pow(2, 0)) * b);
        items.add(total);
        for (int j = 1; j < n; j++) {
            total += ((int)Math.pow(2, j) * b);
            items.add(total);
        }

        items.forEach(i -> System.out.print(i + " "));

        System.out.println(findDay(8, 5, 15));
    }

    public static String findDay(int month, int day, int year) {
        Calendar c = new GregorianCalendar(year, month-1, day);
        System.out.println();
        System.out.println(((GregorianCalendar) c));
        String res = c.getDisplayName(c.get(Calendar.DAY_OF_WEEK), Calendar.LONG, Locale.ENGLISH).toUpperCase();
        System.out.println(res);
        return res;
    }

}

package com.davemilligan.interview.hackerrank;

public class AlternatingCharacters {

    public static void main(String[] args) {
        System.out.println(alternatingCharacters("AAAA"));
    }

    /**
     * Check how mny characters need to be removed to make alternating chars, only A or B present.
     * @param s
     * @return
     */
    static int alternatingCharacters(String s) {
        int count = 0;
        String last = null;
        for (String c : s.split("")) {
            if (last == null) {
                last = c;
                continue;
            }

            if (c.equals(last)) {
                count++;
            }
            last = c;
        }
        return count;
    }
}

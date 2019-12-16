package com.davemilligan.interview.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BracketsMatching {
    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        Map<String, String> isClosing = new HashMap<>();
        isClosing.put("}", "{");
        isClosing.put("]", "[");
        isClosing.put(")", "(");

        Map<String, String> openingmatch = new HashMap<>();
        openingmatch.put("{", "}");
        openingmatch.put("[", "]");
        openingmatch.put("(", ")");

        while (sc.hasNext()) {
            String input=sc.next();
            //Complete the code
            Stack<String> stack = new Stack<>();
            for(String s : input.split("")) {
                //System.out.println("S:" + s);
                if (!stack.empty() &&
                        isClosing.containsKey(s) &&
                        s.equals(openingmatch.get(stack.peek())))
                    stack.pop();
                else
                    stack.push(s);
                //System.out.println(stack);
            }
            System.out.println(stack.empty());
        }

    }
}

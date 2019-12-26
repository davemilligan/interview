package com.davemilligan.interview.hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BracketsMatching {
    public static void main(String []argh)
    {
        Scanner sc = new Scanner(System.in);
        String closingPattern = "}])";

        Map<String, String> openingPattern = new HashMap<>();
        openingPattern.put("{", "}");
        openingPattern.put("[", "]");
        openingPattern.put("(", ")");

        while (sc.hasNext()) {
            String input=sc.next();
            //Complete the code
            Stack<String> stack = new Stack<>();
            for(String s : input.split("")) {
                //System.out.println("S:" + s);
                if (!stack.empty() &&
                        closingPattern.contains(s) &&
                        s.equals(openingPattern.get(stack.peek())))
                    stack.pop();
                else
                    stack.push(s);
                //System.out.println(stack);
            }
            System.out.println(stack.empty());
        }

    }
}

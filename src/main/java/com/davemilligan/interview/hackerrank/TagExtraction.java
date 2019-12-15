package com.davemilligan.interview.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtraction {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        List<String> list = new ArrayList<>();
        while(testCases>0){
            String line = in.nextLine();
            String regex = "<(.+)>([^<]+)<\\/\\1>";
            Pattern p = Pattern.compile(regex);

            Matcher m = p.matcher(line);
            //Write your code here
            boolean found = false;
            while (m.find()) {
                list.add(m.group(2));
                found = true;
            }

            if (!found)
                list.add("None");
            testCases--;
        }

        list.forEach(System.out::println);

    }
}

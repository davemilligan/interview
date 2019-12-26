package com.davemilligan.interview.hackerrank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ArraysSort {
    public static void main(String []args){
        String []s= {"-100","50","0","56.6","90","0.12", ".12","02.34","000.000"};

//        Scanner sc= new Scanner(System.in);
//        int n=sc.nextInt();
//        String []s=new String[n+2];
//        for(int i=0;i<n;i++){
//            s[i]=sc.next();
//        }
//        sc.close();
        //  Write your code here
        //  Sorting in reverse order, maintaining insertion order when numerically equal
        Arrays.sort(s, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1 == null || o2 == null) {
                    return 0;
                }
                BigDecimal od1 = new BigDecimal(o1);
                BigDecimal od2 = new BigDecimal(o2);




                return (od1.compareTo(od2) > 0) ? -1 : 1;
            }
        });


        //Output
        for(int i=0;i<s.length;i++)
        {
            System.out.println(s[i]);
        }
    }
}

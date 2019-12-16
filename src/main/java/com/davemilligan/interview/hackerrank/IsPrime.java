package com.davemilligan.interview.hackerrank;

public class IsPrime {

    void checkPrime(int... args){
        String outPut = "";
        for (int arg : args){
            if (isPrime(arg)){
                outPut += arg+" ";
            }
        }
        if (outPut != ""){
            outPut = outPut.substring(0,outPut.length()-1);
            System.out.print(outPut);
        }
        System.out.println();
    }

    boolean isPrime(int num){
        if (num < 2){
            return false;
        }
        for (int i = 2; i < (num/2)+1; i++){
            if (num%i==0){
                return false;
            }
        }
        return true;
    }
}

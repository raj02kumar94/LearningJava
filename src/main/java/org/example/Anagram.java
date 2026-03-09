package org.example;

import java.util.Arrays;

public class Anagram {

    static void main() {

        String strA = "Geeks";
        String strB = "kseg";

        strA = strA.toLowerCase();
        strB = strB.toLowerCase();

        if(strA.length()==strB.length()){

            char[] ch1 = strA.toCharArray();
            char[] ch2 = strB.toCharArray();

            Arrays.sort(ch1);
            Arrays.sort(ch2);

            boolean result = Arrays.equals(ch1, ch2);
            if(result){
                System.out.println(strA+" "+strB + " is anagram");

            }
        }else{

            System.out.println(strA+" not equal "+strB);
        }

    }
}

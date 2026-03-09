package org.example;

public class ReverseWord {

    static void main() {

        String originalStr = "Hello World";

        String[] word = originalStr.split(" ");

        String reversedStr = "";

        for(int i=0; i<word[0].length(); i++){

            reversedStr = word[0].charAt(i)+reversedStr;
        }

        String result = word[1] + " " + reversedStr;

        System.out.println(result);

    }
}

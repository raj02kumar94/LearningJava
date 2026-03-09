package org.example;

import java.util.ArrayList;
import java.util.List;

public class ReverserNumber {

    public static void main() {

        List<String> stringList =new ArrayList<>();

        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");

        System.out.println(stringList);

        String orginalString ="1234";
        String reversedString = "";

        for(int i=0; i<orginalString.length();i++){

            reversedString=orginalString.charAt(i)+reversedString;

        }

        System.out.print(reversedString);
    }

}

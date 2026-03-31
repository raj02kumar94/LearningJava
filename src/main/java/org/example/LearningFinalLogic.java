package org.example;

import java.util.HashMap;
import java.util.Map;

public final class LearningFinalLogic {

    static int data = 10;

    public  void testFinal() {

        data = 15;
        System.out.println("This is a final method");
    }

    static void main() {



        String orgText ="pppdaf";
        // String uniqueCharacter =



        Map<Character, Integer> test = new HashMap<>();

        char repeatedChar = 0;
        char uniqueChar = 0;
        int count=1;
        // char chh = orgText.toCharArray();

        for(char chh : orgText.toCharArray()){

            if(test.containsKey(chh)){
                test.put(chh, count+1);
                repeatedChar = chh;
                System.out.println(repeatedChar +" is repeated character");
            }else{
                test.put(chh, count);
                uniqueChar =chh;
                System.out.println(uniqueChar +" is unique character");

            }
        }

//        System.out.println(repeatedChar +" is repeated character");
//
//        System.out.println(uniqueChar +" is unique character");


    }

}

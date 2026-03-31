package org.example;

import java.util.HashMap;
import java.util.Map;

public class TestStatic {

    static void main() {
        String input = "Wipro";


        String rev ="";

        for (int i = 1; i < input.length(); i++) {
            String rotated = input.substring(i) + input.substring(0, i).toLowerCase();
            System.out.println(rotated);
        }



//        Map<Character, Integer> set = new HashMap();
//
//        for(char c: input.toCharArray()){
//
//            set.put(c, set.getOrDefault(c,0)+1);
//        }
//
//        for(Map.Entry<Character, Integer> entry :set.entrySet()){
//                if(entry.getValue()==1){
//                    System.out.println(entry.getKey() + " is unique character");
//                }
//
//        }

    }




}

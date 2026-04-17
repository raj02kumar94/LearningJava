package org.example;

import java.util.*;

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

        Map<String, Integer> hMap = new LinkedHashMap<>();

        hMap.put("David",95);
        hMap.put("Jane",80);
        hMap.put("Mary",97);
        hMap.put("Lisa",78);
        hMap.put("Dino",65);

        System.out.println(hMap);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(hMap.entrySet());
        entryList.sort((a,b)-> b.getValue().compareTo(a.getValue()));


        for(Map.Entry<String, Integer> entry: entryList){

            System.out.println(entry.getKey() + ": "+ entry.getValue());
        }


    }
}

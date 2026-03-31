package org.example;

import java.util.LinkedHashMap;

public class OccuranceTest {

    static String originalString = "helloworld";

    public static void countOccurance1(){
        int count = 0;
        for(int i=0; i<originalString.length();i++){
            if(originalString.charAt(i) =='o'){
                count++;
            }
        }
        System.out.println("The number of occurance of 'o' is: " + count);
    }



    static LinkedHashMap<Character, Integer> hMap = new LinkedHashMap<>();
    public static void countOccurrence() {
        for(int i=0; i<originalString.length()-1; i++){
            if(hMap.containsKey(originalString.charAt(i))){
                int count = hMap.get(originalString.charAt(i));
                hMap.put(originalString.charAt(i), count+1);

            }else{
                hMap.put(originalString.charAt(i), 1);
            }
        }
        System.out.print(hMap);

        for(Character ch : originalString.toCharArray()) {

            hMap.put(ch, hMap.getOrDefault(ch, 0) + 1);
        }

        System.out.println(hMap);
//    }

    }


    public static void main() {
        countOccurance1();
        countOccurrence();
    }

}

package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicateElements {

    static void main() {

        int [] b = {1,2,3,4,5,6,7,8,9,1,2};

        Set<Integer> duElements = new HashSet<>();
        Set<Integer> duElements1 = new HashSet<>();

        for (int k : b) {
            if(duElements.contains(k)){
                duElements1.add(k);
            }else {

                duElements.add(k);
            }
        }

        Map<Integer, Integer> charMap = new HashMap<>();
        String input = "pppdaf";

        for(int ch: b){

            charMap.put(ch, charMap.getOrDefault(ch, 0)+1);

        }

        System.out.println(charMap);

        for(Map.Entry<Integer, Integer> entry : charMap.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.println(entry.getKey() + " is unique character");
            }
        }

        System.out.println(duElements);
        System.out.println(duElements1);



//        for(Map.Entry(Character, Integer)  : charMap.entrySet()){
//
//
//        }


//        }



//        for(int i=0;i<b.length;i++){
//            for(int j=i+1;j<b.length;j++){
//                if(b[i]==b[j]){
//                    System.out.println(b[i]);
//                }
//            }
//        }

        String [] str = {"java","python","ruby","java","ruby"};
//        String ttt = "pppdaf";

        Set<String> dupStr = new HashSet<>();
        Set<String> dupStr1 = new HashSet<>();

        for(String er: str){

            if(dupStr.contains(er)){
                dupStr1.add(er);
            }else{
                dupStr.add(er);
            }
        }
        System.out.println(dupStr);
        System.out.println(dupStr1);

//        for(int i=0;i<str.length;i++){
//            for(int j=i+1;j<str.length;j++){
//                if(str[i].equals(str[j])){
//                    System.out.println(str[i]);
//                }
//            }
//        }

    }

}

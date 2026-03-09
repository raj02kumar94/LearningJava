package org.example;

import java.util.HashSet;
import java.util.Set;

public class DuplicateElements {

    static void main() {

        int [] b = {1,2,3,4,5,6,7,8,9,1,2};

        Set<Integer> duElements = new HashSet<>();

        for (int k : b) {

            duElements.add(k);
        }
        System.out.println(duElements);

//        for(int i=0;i<b.length;i++){
//            for(int j=i+1;j<b.length;j++){
//                if(b[i]==b[j]){
//                    System.out.println(b[i]);
//                }
//            }
//        }

        String [] str = {"java","python","ruby","java","ruby"};

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

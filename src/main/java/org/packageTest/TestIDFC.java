package org.packageTest;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TestIDFC {


    public static void main (String[] args){

        String text = """
                The quick brown fox jumps over the lazy dog.
                The dog barked at the fox, but the fox ran away,
                A lazy dog is not a goo guard dog,
                The quick fox is faster than the lazy dog.
                Every dog has its day, and every fox has its night.
                """;


        String[] stop = {"the", "a","an","is","and","but","or","at","to","of"};

        int top_n =5;

        String[] nextLine = text.split("\\.");
        String[] textSplit = text.split(" ");

        int getLine =0;


        Map<String, Integer> hMap = new HashMap<>();

        for (String txt: textSplit){

            hMap.put(txt, hMap.getOrDefault(txt,0)+1);
        }
//        Map<String, Integer> stopMap = new HashMap<>();
        Set<String> setValue = new LinkedHashSet<>();
        Set<String> uniqueValue = new LinkedHashSet<>();
        for (Map.Entry<String,Integer> listMap : hMap.entrySet()){

            for(int i =0; i< textSplit.length; i++){

                for(int j=i; j< textSplit.length;j++){
                    for (String s : stop) {

                        if (listMap.getKey().equals(s)) {
                            uniqueValue.add(listMap.getKey());
                        }else{
                            setValue.add(listMap.getKey());
                        }
                        if(listMap.getKey().contains(text)){

                            getLine++;
                            System.out.println("Word: \n"+listMap.getKey() +" Frequency: \n"+ listMap.getValue()+ "Lines: \n"+ getLine);
                        }

                    }

                }
            }

        }

        System.out.println(hMap);
        System.out.println(setValue+" "+setValue.size());
        System.out.println(uniqueValue+" "+uniqueValue.size());


    }

}

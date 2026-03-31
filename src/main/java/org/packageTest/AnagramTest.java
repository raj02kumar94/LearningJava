package org.packageTest;

import java.util.Arrays;

public class AnagramTest {




    static void main() {

        String str1 = "listen";
        String str2 = "silent";

        str1 = str1.toLowerCase();

        str2 = str2.toLowerCase();

        if(str1.length()==str2.length()){

            char[] ch1 = str1.toCharArray();
            char[] ch2 = str2.toCharArray();

            Arrays.sort(ch1);
            Arrays.sort(ch2);

           boolean checkCondition =  Arrays.equals(ch1, ch2);


           if(checkCondition)

                System.out.println(str1+" and "+str2+" is anagram");
              else
                System.out.println(str1+" and "+str2+" is not anagram");





        }
    }
}

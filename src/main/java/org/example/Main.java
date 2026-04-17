package org.example;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//        System.out.println("Try programiz.pro");
//
//
//        int [] a ={1,2,3,4,5,1,2,5};
//
//        HashMap<Integer, Integer> hMap = new HashMap<>();
//
//        for(int i= a.length-1; i>=0; i--){
//            if(hMap.containsKey(a[i])){
//                int count = hMap.get(a[i]);
//
//                hMap.put(a[i], count+1);
//
//            }else{
//
//                hMap.put(a[i],1);
//            }
//
//        }
//        System.out.println("Occurrance for each number"+hMap);
//        int[] b = new int[0];
//        Set<Integer> set = new HashSet<>();
//
//        for(int k= a.length-1; k>=0; k--){
//
//                set.add(a[k]);
//        }
//        System.out.println("After Removing Duplicate Elements:"+set.toString());

        List<String> fruits = new ArrayList<>();

        fruits.add("Orange");
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Pear");
        fruits.add("Strawberry");

       Collections.sort(fruits);

       System.out.println(fruits);

       HashMap<String, String> hMap = new HashMap<>();

       hMap.put("Fruits", "Banana");
       hMap.put("Red", "Apple");
       hMap.put("Color", "Orange");

       System.out.println(hMap);
       Set<String> keys = hMap.keySet();
       for (String key : keys) {
           System.out.println(hMap.get(key));
       }

       String strC = "Dormitory";

       String revStr ="";

       StringBuilder sb = new StringBuilder(strC).reverse();

       System.out.println(sb);
       for(int i=0; i<strC.length();i++){

           revStr = strC.charAt(i)+revStr;
       }

       System.out.println(revStr);


       String str1 = "Test";
       String str2 = "Str2";

       String str3 ="";

       str3 = str1;
       str1 = str2;
       str2 = str3;

       System.out.println(str1 + str2);


       int i1 = 10;
       int i2 = 30;

       i1 = i1+i2;
       i2 = i1-i2;
       i1 = i1-i2;

        System.out.println(i1);
        System.out.println(i2);


        int n1 =0;
        int n2=1;
        int n3 = 0;
        System.out.println(n1);
        System.out.println(n2);

        for(int i=2; i<10; i++){
            n3=n1+n2;
            n1=n2;
            n2=n3;
            System.out.println(n3);
        }


        // Calling method from TestAbstract
        class SubTest extends TestAbstract {
            void testMethod() {
                System.out.println("Abstract method implemented");
            }
        }

        SubTest obj = new SubTest();
        obj.testMethod1();

    }
}

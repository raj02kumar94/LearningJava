package org.example;

public class OddEvenTest

{


   public static void main() {

       int[] num={2,4,5,7};

       for(int nums:num) {
           if (nums % 2 == 0) {
               System.out.println(nums + " is an even number");
           } else {
               System.out.println(nums+ " is an odd number");
           }
       }
    }
}

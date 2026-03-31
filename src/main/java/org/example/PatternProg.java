package org.example;

public class PatternProg {


    static void main() {

        int s =5;

        for(int i=1; i<=s; i++){

            for(int j=i; j<s-2; j++){

                System.out.print(" ");
            }

            for(int k=1; k<=i; k++){

                System.out.print(k);
            }

            System.out.println();

        }
    }
}

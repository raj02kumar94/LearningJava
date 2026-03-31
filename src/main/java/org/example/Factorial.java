package org.example;

public class Factorial {


    static void main() {

        String input = "5!";

        String numStr = input.substring(0, input.length()-1);

        int num = Integer.valueOf(numStr);

        String offValue = String.valueOf(num);

        int factorial = 1;

        for(int i=0; i<num;i++){

            factorial*=i+1;
        }

        System.out.println(factorial);


        // Original string
        String originalString = "Java";
        System.out.println("Original string: " + originalString);
        // Attempt to modify the original string
        String modifiedString = originalString.concat(" Programming");
        // Showing that the original string remains unchanged
        System.out.println("After modification, original string: " + originalString);
        // The result of the modification attempt is stored in a new string
        System.out.println("Modified string: " + modifiedString);
        // Demonstrating further that the original string is immutable
        originalString.toUpperCase(); // This operation does not change the original string
        System.out.println("After calling toUpperCase on original string: " + originalString);
        // Correct way to use the result of a string operation
        String upperCaseString = originalString.toUpperCase(); // Stores the result in a new string
        System.out.println("Original string in uppercase: " + upperCaseString);

        StringBuffer dBuffer = new StringBuffer("Hello");
        dBuffer.append("Test");

        System.out.println(dBuffer);;
        originalString.concat("Test");

        System.out.println(originalString);


    }
}

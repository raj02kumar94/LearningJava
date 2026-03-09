package org.example;

public class PalindromeTest {


    public static void checkPalindrome(){

        String orginalStr = "ABCBA2";

        String revStr = "";

        for(int i=0; i<orginalStr.length(); i++) {
            revStr = orginalStr.charAt(i)+revStr;
        }
        if(orginalStr.equals(revStr))
            System.out.println("Given string is palindrome");
        else
            System.out.println("Given string is not palindrome");

    }

    static void main() {
        checkPalindrome();
    }
}

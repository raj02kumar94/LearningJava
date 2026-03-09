package org.example;

public class MissingNumberinArray {

    public static void main(String[] args) {

        int[] a={1,2,3,4,5,6,7,9};
//        int[] a = {101,102,103,105};

        // Find the missing number
        int missing = findMissingNumber(a);
        System.out.println("Missing number: " + missing);
    }

    static int findMissingNumber(int[] a) {
        // Calculate expected sum of consecutive numbers from first to last element
        int first = a[0];
        int last = a[a.length - 1];
        int n = last - first + 1; // Total numbers in the sequence

        // Sum of arithmetic sequence: n * (first + last) / 2
        int expectedSum = n * (first + last) / 2;

        // Calculate actual sum
        int actualSum = 0;
        for (int num : a) {
            actualSum += num;
        }

        // Missing number
        return expectedSum - actualSum;
    }
}

package org.example;

import java.util.Collections;

/**
 * This class contains methods to find the second largest number in an array.
 */
public class SecondLargestNumber {

    /**
     * Finds and returns the second largest number in a predefined array.
     * The method sorts the array in ascending order using a nested loop.
     *
     * @return The second largest number in the array.
     */
    public static int getSecondLargest() {
        int nums[] = {15, 12, 33, 22, 87}; // Predefined array of integers

        int firstLargest = 0; // Temporary variable for swapping

        // Nested loop to sort the array in ascending order
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    firstLargest = nums[i];
                    nums[i] = nums[j];
                    nums[j] = firstLargest;
                }
            }
        }

        System.out.println(nums); // Print the sorted array (not meaningful in this context)

        return nums[0]; // Return the second element, which is the second largest
    }

    /**
     * Main method to execute the program. Calls the getScondLargest method
     * and prints the result.
     */
    static void main() {
        getSecondLargest(); // Call the method to find the second largest number

        System.out.println("" + getSecondLargest()); // Print the result

        int[] a ={1,2,4,3,2,34,33};

        int largest = Integer.MIN_VALUE;

    }






}

/**
 * Prints the second-largest distinct number from a hard-coded array.
 * If the array has fewer than two elements or all elements are equal,
 * a clear message is printed.
 */
 class SecondLargestNumber1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 22, 87};

        if (nums == null || nums.length < 2) {
            System.out.println("Array must contain at least two elements");
            return;
        }
//        Collections.sort(nums);

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        // Single pass to find largest and second largest (distinct)
        for (int n : nums) {
            if (n > largest) {
                secondLargest = largest;
                largest = n;
            } else if (n > secondLargest && n < largest) {
                secondLargest = n;
            }
        }

        // If no distinct second largest was found, report it
        if (secondLargest == Integer.MIN_VALUE) {
            boolean hasDifferent = false;
            for (int n : nums) {
                if (n != largest) {
                    hasDifferent = true;
                    break;
                }
            }
            if (!hasDifferent) {
                System.out.println("No second largest (all elements are equal)");
            } else {
                // This branch handles the rare edge case where secondLargest
                // equals Integer.MIN_VALUE but a distinct smaller value exists.
                System.out.println(secondLargest);
            }
        } else {
            System.out.println(secondLargest);
        }
    }
}

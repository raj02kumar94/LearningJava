package org.example;

public class LetterAPattern {

    public static void main(String[] args) {
        int height = 7; // Height of the letter
        int width = 5;  // Width of the letter

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Logic for printing the 'A' shape
                if ((i == 0 && j > 0 && j < width - 1) || // Top bar (curved)
                        (i == height / 2) ||                  // Middle bar
                        (j == 0 && i > 0) ||                  // Left side
                        (j == width - 1 && i > 0)) {          // Right side
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to next line
        }
    }
}

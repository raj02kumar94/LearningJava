package org.example;

public class LearningCollections {

    static void main() {

        int arr1[] = {1,2,3,4,5};
        int arr2[] = {1,7,8,3,10};

        for(int i=0; i<arr1.length;i++){

            for(int j=0; j<arr2.length;j++){

                if(arr1[i]==arr2[j]){
                    System.out.println(arr1[i]);
                }
            }
        }

        String inputText = "A1B2C3D4E5";

        StringBuilder alpha = new StringBuilder();

        for(int i=0; i<inputText.length(); i+=2){

            char letter = inputText.charAt(i);

            int count = Character.getNumericValue(inputText.charAt(i+1));

            for(int j=0; j<count; j++){

                alpha.append(letter);

            }
            alpha.append(" ");

        }

//        System.out.println("num: " + alpha1.toString());
//        System.out.println("alpha: " + alpha.toString());

        System.out.println(alpha.toString().trim());

    }
}

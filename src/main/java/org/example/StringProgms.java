package org.example;

public class StringProgms {


    static void main() {

        String s = "9876543210";
//                "ABCDEFGH";

        // ABC BCD CDE DEF EFG FGH

        int arr[]={1,2,3,4,5,6};

        for (int i = 0; i < s.length()-2; i++) {
            System.out.print(s.substring(i, i + 3) + " ");
        }

        int firstLargest =0;
        for(int k=0;k<arr.length; k++){

            for(int p=k; p< arr.length; p++){

                if(arr[k]<arr[p]){

                    firstLargest = arr[k];
                    arr[k]=arr[p];
                    arr[p]=firstLargest;
                }

//                if(arr[k]+arr[p]==4){
//
//                    System.out.println(arr[k] + " " + arr[p]);
//                }

            }


        }

        System.out.println(arr[1]);






        String strEle = "Selenium";

        int count =0;

        StringBuilder stringBuilder = new StringBuilder();

        for(int m=0; m<strEle.length(); m++){

            if(strEle.charAt(m) == 'e'){
                count++;
                stringBuilder.append("*".repeat(count));
            }else {

                stringBuilder.append(strEle.charAt(m));
            }

        }

        System.out.println(stringBuilder.toString());



    }





}

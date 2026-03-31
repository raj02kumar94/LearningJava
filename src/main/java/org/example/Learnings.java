package org.example;

import java.util.Arrays;

public class Learnings implements TestInter{





//    Instance variable will be declared inside the class and outside the method. We can access this method anywhere in the same class

    int data = 11;

//    Local variable will be declared inside the method and we can access this inside the method alone we cannot access it outside the method or class.

    public void testData1(){

        int checkData = 12;

        System.out.println(checkData + " is local variable");

        System.out.println(data + " is instance variable");

    }




































//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Learnings.class);

    public void tests(){
        LearningFinalLogic logic = new LearningFinalLogic();

        logic.testFinal();

        logic.data=12;

    }

    static void main() {




//        int[] arr = {1001,1002,1003,1005};

        int[] arr = {1,12,23,14,25,26,7,19};

        Arrays.sort(arr);

        for(int i=0; i<arr.length-1; i++){
            int current = arr[i];
            int next = arr[i+1];
            for(int num =current+1; num<next; num++){

                System.out.println(num);
            }
        }

        int first = arr[0];
        int last = arr[arr.length-1];
        int expected = last - first+1;
        int n = expected*(first+last)/2;

        int sum = 0;

        for(int num :arr){

            sum+=num;
        }

        n = n-sum;

        System.out.println(n);

//        checkLocalVariable();
//        checkInstanceVariable();

        char ch;
        int i=0;

        for(ch='A', i=1; ch<='Z'; i++, ch++){

            System.out.println(ch + " = " + i);
        }

        for(int j=1; j<=26; j++){

            System.out.println((char)(j+64) + " = " + j);
        }

        for(int k=0; k<4; k++){

            for(int l=0; l<4; l++){

                System.out.print("* ");
            }
            System.out.println();
        }

        int q=4;

        for(int m=0; m<q; m++){

            for(int p = q-1; p<=m; p++){

                System.out.print("* ");
            }
            System.out.println();
        }



    }

//    Variable is container used to store values. Every variable must be assigned with data type(int,float, double, char, String)


//    It has three type: 1. Local Variable, 2. Instance Variable 3. Static Variable.


//    Local variable ---> It means we can declare variable inside the method. It cannot call or use outside the method or class.
//    Below is the example of local variable.

     void checkLocalVariable(){

        String data = "TestWorld";

        System.out.println(data);
        System.out.println("Calling Instance variable inside static method: "+name);
    }

//    Instance variable ===> It means outside the method, but we can call inside the method as well.
//    Below is the example of instance variable.

     String name = "TestWorld";

     void checkInstanceVariable(){

        System.out.println(this.name);
    }

//    Static variable ---> It means once we declared we cannot modify the value.
//    Below is the example of static variable.

    final static String name1 = "TestWorld";

    static void checkStaticVariable(){

//        name1 = name1.replace(name1, "TestConcat");

        System.out.println(name1);
//        System.out.println("Calling Instance variable inside static method: "+name);
    }

    @Override
    public void mainTest() {
        System.out.println("This is main method from interface");
    }
}

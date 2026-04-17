package org.example;

public interface TestInter {

    abstract void mainTest();


    public default void testMethod(){

        System.out.println("Test Default");
    }

    public static final int valoume =0;



}

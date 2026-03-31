package org.packageTest;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class ListTest {


    static void main() throws IOException {

//        List allows duplicate values and maintains insertion order
//        List is an interface in Java that represents an ordered collection of elements.
//        It allows duplicate values and maintains the insertion order of the elements.
//        The most commonly used implementation of the List interface is ArrayList, which provides dynamic resizing and efficient access to elements.

//        There are several implementation of List interface in Java, includes ArrayList, LinkedList, Vector and Stack.
//        Each implementation has its own characteristics and use cases.
//        For example, ArrayList is generally faster for random access and is suitable for most use cases,
//        while LinkedList is better for scenarios that involve frequent insertions and deletions.

        List<String> stringList = new ArrayList<>();

        stringList.add("2");
        stringList.add("2");
        stringList.add("3");
        stringList.add("1");
        stringList.add("5");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");

        System.out.println(stringList);

//        stringList.remove("3"); to remove the first occurrence of "3" from the list

        stringList.removeIf(str -> str.equals("2"));

//        stringList.clear();  System.out.println("After Clear : "+stringList);// to remove all elements from the list

        System.out.println("After Remove : "+stringList);

        List<String> strings = new LinkedList<>();
        strings.add("One");
        strings.add("Two");
        strings.add("Three");
        strings.add("One");

        System.out.println(strings);

        Workbook workbook = new XSSFWorkbook("C:\\Users\\Raj02\\OneDrive\\Desktop\\Book1.xlsx");

        Sheet sheet =workbook.getSheet("Sheet1");

        int getRowCount = sheet.getLastRowNum();

        System.out.println("Row Count : "+getRowCount);






    }
}

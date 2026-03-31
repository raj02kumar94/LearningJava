package org.packageTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TestWipro {

    static void main() {

        String[] Input= { "Apple", "Microsoft", "Google", "Amazon", "Microsoft", "Google"};

//        Output: {"Microsoft", "Google"}

        Map<String,Integer> dup1 = new HashMap<>();
        Set<String> dup2 = new HashSet<>();
        Set<String> dup3 = new HashSet<>();

        for(String str : Input){

            if(dup2.contains(str)){
                dup3.add(str);
            }else{
                dup2.add(str);
            }

            dup1.put(str, dup1.getOrDefault(str, 0)+1);

        }

        System.out.println(dup2 + " is unique element");
        System.out.println(dup3 + " is duplicate element");

        for(Map.Entry<String, Integer> entry: dup1.entrySet()){

            if(entry.getValue()>1){

                dup2.add(entry.getKey());
                System.out.println(entry.getKey() + " is duplicate element");
            }
        }


//        WebDriver driver= new ChromeDriver();
//
//        driver.get("https://www.apple.com/");
//
//        JavascriptExecutor jse = (JavascriptExecutor) driver;
//
//        jse.executeScript("arguments[0].scrollIntoView('true');", driver.findElement(By.xpath("//li[@class='ac-gf-directory-column-section-item']/a[text()='Order Status']")));
//        jse.executeScript("arguments[0].click();", driver.findElement(By.xpath("//li[@class='ac-gf-directory-column-section-item']/a[text()='Order Status']")));
//
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-labelledby='apple_id_field_label']"))).sendKeys("raj02kumar94@yahoo.com"+Keys.ENTER);






    }
}

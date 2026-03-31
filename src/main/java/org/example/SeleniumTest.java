package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Set;

public class SeleniumTest {

    static void main() {

        SeleniumManager.getInstance();

        WebDriver driver = new ChromeDriver();


        WebElement element = driver.findElement(By.id("elementId"));

        Select select = new Select(element);

//        select.selectByVisibleText();
//
//        select.selectByValue();

        RelativeLocator.with(By.tagName("input")).below(element);

        RelativeLocator.with(By.xpath("//input[@type='text']")).toLeftOf(element);

        String windowHandle = driver.getWindowHandle();

        Set<String> windows = driver.getWindowHandles();

        for(String switchWindow: windows){

            if (!windowHandle.equals(switchWindow)) {


                driver.switchTo().window(switchWindow);


            }
        }


        File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);




    }





}

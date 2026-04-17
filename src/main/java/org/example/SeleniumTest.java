package org.example;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.manager.SeleniumManager;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class SeleniumTest {

    static void main() throws IOException {

//        SeleniumManager.getInstance();
//
//        WebDriver driver = new ChromeDriver();
//
//
//        WebElement element = driver.findElement(By.id("elementId"));
//
//        Select select = new Select(element);
//
////        select.selectByVisibleText();
////
////        select.selectByValue();
//
//        RelativeLocator.with(By.tagName("input")).below(element);
//
//        RelativeLocator.with(By.xpath("//input[@type='text']")).toLeftOf(element);
//
//        String windowHandle = driver.getWindowHandle();
//
//        Set<String> windows = driver.getWindowHandles();
//
//        for(String switchWindow: windows){
//
//            if (!windowHandle.equals(switchWindow)) {
//
//
//                driver.switchTo().window(switchWindow);
//
//
//            }
//        }
//

//        File scrShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);


        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\Raj02\\OneDrive\\Pictures\\Albums\\Documents\\TestDocument.xlsx");

        XSSFSheet sheet = workbook.getSheet("sheet1");

        int row = sheet.getLastRowNum();

        for(int i=1;i<=row;i++){
            for(int j=0;j<sheet.getRow(i).getLastCellNum();j++){
                String data = sheet.getRow(i).getCell(j).getStringCellValue();

                System.out.println(data + " ");
            }
        }

//        FluentWait<WebDriver> wait = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(10))
//                .poolingEvery(Duration.ofSeconds(2))
//                .igonoring(NoSuchElementException.class);
//
////        WebElement element =



    }





}

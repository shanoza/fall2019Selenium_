package com.automation.homework.number4;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainDepartments {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**
     * 1.go to https://www.amazon.com/gp/site-directory
     * 2.verify that every main department name
     * (indicated by blue rectangles in the picture)is
     * present in the All departments dropdown
     * (indicated by green rectangle in the picture)
     */
    @Test
    public void verifyPresence() {

        //Step 1.go to https://www.amazon.com/gp/site-directory
        driver.get("https://www.amazon.com/gp/site-directory");

        List<WebElement> mainDepartments = driver.findElements(By.className("fsdDeptTitle"));
        List<String> mainDep = new ArrayList<>();

        for (WebElement eachDep : mainDepartments) {
            mainDep.add(eachDep.getText());
        }

        String[] mainArray = mainDep.toArray(new String[mainDep.size()]);

        Arrays.sort(mainArray);
        String main = Arrays.toString(mainArray).toLowerCase();
        System.out.println(main);

        System.out.println("========");

        List<WebElement> allDepartments = driver.findElements(By.xpath("//select[@id='searchDropdownBox']//option"));
        List<String> copyOfAllDepartments = new ArrayList<>();

        for (WebElement eachDepartment : allDepartments) {
            copyOfAllDepartments.add(eachDepartment.getText());
        }

        String[] allArray = copyOfAllDepartments.toArray(new String[copyOfAllDepartments.size()]);

        Arrays.sort(allArray);
        String all = Arrays.toString(allArray).toLowerCase();
        System.out.println(all);

        //verified all main department names are not present in all department list
        Assert.assertFalse(all.contains(main));

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}


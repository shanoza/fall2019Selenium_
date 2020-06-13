package com.automation.homework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HW_DataProviderExample {

    @Test(dataProvider = "searchProvider")
    public void test(String url, String searchKey) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get(url);

        Assert.assertTrue(driver.getTitle().contains(searchKey));
        driver.quit();
    }

    @DataProvider(name = "searchProvider")
    public Object[][] testData() {
        return new Object[][]{
                {"https://www.amazon.com", "Amazon"},
                {"https://www.google.com/","Google"},
                {"https://www.apple.com", "Apple"},
                {"https://www.youtube.com/", "YouTube"}
        };
    }
}



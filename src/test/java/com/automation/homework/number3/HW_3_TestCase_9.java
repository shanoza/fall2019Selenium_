package com.automation.homework.number3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HW_3_TestCase_9 {

    private WebDriver driver;



    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "searchProvider")
    public void test(String name, String searchKey) {
        WebElement searchText = driver.findElement(By.xpath("//a[text()='" + name+ "']"));
        searchText.click();
        BrowserUtils.wait(3);
        WebElement statusCode = driver.findElement(By.xpath("//a[text()='" + searchKey + "']"));
        statusCode.click();
        BrowserUtils.wait(3);
        WebElement message = driver.findElement(By.id("content"));
        Assert.assertTrue(message.getText().contains(searchKey));
    }

    @DataProvider(name = "searchProvider")
    public Object[][] testData() {
        return new Object[][]{
                {"Status Codes", "200"},
                {"Status Codes", "301"},
                {"Status Codes", "404"},
                {"Status Codes", "500"}
        };
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

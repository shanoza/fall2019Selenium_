package com.automation.homework.number3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HW_3_TestCase_8 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void inAction(){
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America", Keys.ENTER);
        driver.findElement(By.xpath("//input[@type='button']")).click();
        BrowserUtils.wait(3);
        String expected="You selected: United States of America";
        String actual= driver.findElement(By.xpath("//p[@id='result']")).getText();
        Assert.assertEquals(actual,expected);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}

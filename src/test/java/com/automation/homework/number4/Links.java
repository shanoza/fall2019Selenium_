package com.automation.homework.number4;


import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
public class Links {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void displayElements() {
        driver.get("https://www.w3schools.com/");
        List<WebElement> href = driver.findElements(By.xpath("//a[@href]"));
        for (WebElement eachLink : href) {
            System.out.println(eachLink.getAttribute("href"));
        }
//        BrowserUtils.wait(3);
        List<WebElement> text = driver.findElements(By.xpath("//a[contains(text(),'')]"));
        for (WebElement eachText : text) {
            System.out.println(eachText.getText());
        }
//        BrowserUtils.wait(3);
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}


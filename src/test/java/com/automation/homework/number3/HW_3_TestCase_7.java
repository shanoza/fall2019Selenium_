package com.automation.homework.number3;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HW_3_TestCase_7 {

    private WebDriver driver;
    private By fileUpload=By.linkText("File Upload"); // xpath: //a[@href='/upload']
    private By uploadButton=By.id("file-submit");
    private By chooseFileButton=By.xpath("//input[@id='file-upload']");
    private By fileUploadedMessage=By.xpath("//h3[text()='File Uploaded!']");
    private By uploadedFileName=By.id("uploaded-files");


    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test()
    public void testCase7() {
        driver.findElement(fileUpload).click();
        BrowserUtils.wait(3);
        //choose file
        driver.findElement(chooseFileButton).sendKeys("//Users//shakhnozanorkulova//Desktop//testngnotes.pdf");
        BrowserUtils.wait(3);
        driver.findElement(uploadButton).click();
        String subjectExpected = "File Uploaded!";
        String actual = driver.findElement(fileUploadedMessage).getText();
        Assert.assertEquals(actual, subjectExpected);
        Assert.assertTrue(driver.findElement(uploadedFileName).isDisplayed());
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}


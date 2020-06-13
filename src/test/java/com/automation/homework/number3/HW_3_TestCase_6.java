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
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HW_3_TestCase_6 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test6() {
        //go to website
        driver.get("https://www.tempmailaddress.com/");
        WebElement getEmail = driver.findElement(By.id("email"));
        //string for dynamic email address
        String email = new String(getEmail.getText());
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        //provide information to sign up
        driver.findElement(By.name("full_name")).sendKeys("Cheehuu");
        driver.findElement(By.name("email")).sendKeys(email);
        BrowserUtils.wait(1);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(1);
        //to make sure the message is displayed
        WebElement signUpMessage=driver.findElement(By.cssSelector("[name='signup_message']"));
        Assert.assertTrue(signUpMessage.isDisplayed());
        BrowserUtils.wait(1);
        driver.navigate().to("https://www.tempmailaddress.com/");
        //find the recieved email cybertek and verify
        WebElement receivedEmail = driver.findElement(By.cssSelector(".from"));
        String emailReceived = receivedEmail.getText().trim();
        String expected = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(emailReceived, expected);
        receivedEmail.click();

        WebElement from = driver.findElement(By.cssSelector("[id='odesilatel']"));
        String actual = from.getText().trim();
        Assert.assertEquals(actual, expected);

        WebElement subject = driver.findElement(By.cssSelector("[id='predmet']"));
        String subjectActual = subject.getText().trim();
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(subjectActual, expectedSubject);
        BrowserUtils.wait(1);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}





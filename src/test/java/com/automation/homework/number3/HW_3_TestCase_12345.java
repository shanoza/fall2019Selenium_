package com.automation.homework.number3;

import com.automation.utilities.BrowserUtils;
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

public class HW_3_TestCase_12345 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test1() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//a[contains(text(),'Registration Form')]")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.xpath("//input[@placeholder='MM/DD/YYYY']")).sendKeys("wrong_dob");
        WebElement actual = driver.findElement(By.xpath("//small[contains(text(),'The date of birth is not valid')]"));

        if (actual.isDisplayed()) {
            System.out.println("Passed");

        } else {
            System.out.println("Failed");
        }
    }

    @Test
    public void Test2() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//a[contains(text(),'Registration Form')]")).click();
        BrowserUtils.wait(2);
//        driver.findElement(By.id("inlineCheckbox1")).click();
//        driver.findElement(By.id("inlineCheckbox2")).click();
//        driver.findElement(By.id("inlineCheckbox3")).click();
//        BrowserUtils.wait(2);

        List<WebElement> listOfElements = driver.findElements(By.className("form-check-input"));
        for (WebElement each : listOfElements) {
            if (each.isDisplayed()) {
                System.out.println("Passed");
            } else {
                System.out.println("failed");
            }
        }

    }

    @Test
    public void Test3() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.xpath("//a[contains(text(),'Registration Form')]")).click();
        BrowserUtils.wait(1);
        driver.findElement(By.name("firstname")).sendKeys("A");
        WebElement verify = driver.findElement(By.xpath("//small[contains(text(),'first name must be more than 2 and less than 64 ch')]"));
        verify.isDisplayed();
        BrowserUtils.wait(2);
        if (verify.isDisplayed()) {
            System.out.println("first name must be more than 2 and less than 64 " +
                    "characters long");
        } else {
            System.out.println("error");
        }
        System.out.println(verify.isDisplayed());

    }

    @Test
    public void Test4() {

        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.xpath("//a[contains(text(),'Registration Form')]")).click();
        BrowserUtils.wait(1);
        driver.findElement(By.name("lastname")).sendKeys("A");
        WebElement actual1 = driver.findElement(By.xpath("//small[contains(text(),'last name must be more than 2 and less than 64 ch')]"));
        actual1.isDisplayed();
        BrowserUtils.wait(2);
        if (actual1.isDisplayed()) {
            System.out.println("first name must be more than 2 and less than 64 " +
                    "characters long");
        } else {
            System.out.println("error");
        }
        System.out.println(actual1.isDisplayed());
    }

    @Test
    public void Test5() {
        driver.get("http://practice.cybertekschool.com/registration_form");
        driver.findElement(By.name("firstname")).sendKeys("John");
        driver.findElement(By.name("lastname")).sendKeys("Smith");
        driver.findElement(By.name("username")).sendKeys("jsmith");
        driver.findElement(By.name("email")).sendKeys("jsmith@email.com");
        driver.findElement(By.name("password")).sendKeys("supersecretpassword2020");
        driver.findElement(By.name("phone")).sendKeys("571-343-2342");
        List<WebElement> genders = driver.findElements(By.name("gender"));
        genders.get(1).click();
        driver.findElement(By.name("birthday")).sendKeys("01/01/2007");
        driver.findElement(By.name("department")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Department of Engineering')]")).click();
        driver.findElement(By.name("job_title")).click();
        driver.findElement(By.xpath("//option[contains(text(),'Designer')]")).click();
        driver.findElement(By.id("inlineCheckbox2")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        BrowserUtils.wait(3);
        WebElement wellDoneMessage = driver.findElement(By.xpath("//p[contains(text(),\"You've successfully completed registration!\")]"));
        System.out.println(wellDoneMessage.isDisplayed());
        System.out.println(wellDoneMessage.getText());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

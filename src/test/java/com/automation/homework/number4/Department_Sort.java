package com.automation.homework.number4;
import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import java.util.concurrent.TimeUnit;
public class Department_Sort {
    WebDriver driver;
    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void dropdownSorting() {

        // Step 1.go to https://www.amazon.com
        driver.get("https://www.amazon.com");

        // Step 2.verify that default value of the All departments dropdown is "All"
        WebElement dropdown = driver.findElement(By.xpath("//span[text()='All']"));
        Assert.assertEquals(dropdown.getText(), "All");


        // Step 3. verify that options in the All departments dropdown are not sorted alphabetically
        List<WebElement> listOfDepartments = driver.findElements(By.xpath("//select[@id='searchDropdownBox']//option"));
        ArrayList<String> copyOfOriginalList = new ArrayList<>();
        for (WebElement eachDepartment : listOfDepartments) {
            copyOfOriginalList.add(eachDepartment.getText());
        }

        ArrayList<String> sortedListOfDepartments = new ArrayList<>(copyOfOriginalList);

        Collections.sort(sortedListOfDepartments);
        Assert.assertNotEquals(copyOfOriginalList, sortedListOfDepartments);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

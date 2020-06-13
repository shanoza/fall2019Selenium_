package com.automation.homework;

import com.automation.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HW_4 {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void days() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
//        driver.manage().window().maximize();
        WebElement checkBox = driver.findElement(By.id("gwt-debug-cwCheckBox-Thursday-input"));
        BrowserUtils.wait(3);
        System.out.println(checkBox.isSelected());
        for (int i = 0; i < 6; i++) {
            checkBox.click();
            BrowserUtils.wait(3);
        }
        BrowserUtils.wait(3);
    }

    @Test
    public void todayDate() {
        driver.get("http://practice.cybertekschool.com/dropdown");
//        driver.manage().window().maximize();
        WebElement year = driver.findElement(By.id("year"));
        year.isDisplayed();
        WebElement month = driver.findElement(By.id("month"));
        month.isDisplayed();
        WebElement day = driver.findElement(By.id("day"));
        day.isDisplayed();
        BrowserUtils.wait(3);
        System.out.println(year.getText().contains("2020"));
        System.out.println(month.getText().contains("April"));
        System.out.println(day.getText().contains("30"));
    }

    @Test
    public void yearsMonthsDays() {
        driver.get("http://practice.cybertekschool.com/dropdown");

        WebElement year = driver.findElement(By.id("year"));
        WebElement month = driver.findElement(By.id("month"));
        WebElement day = driver.findElement(By.id("day"));
        Select y = new Select(year);
        Select m = new Select(month);
        Select d = new Select(day);
        Random r = new Random();
        int index = r.nextInt(y.getOptions().size());
        y.selectByIndex(index);
        List<String> months31 = new ArrayList<>(Arrays.asList(new String[]{"January", "March", "April", "May",
                "July", "August",
                "October", "December"}));
        int fevDays;
        fevDays = Integer.parseInt(y.getFirstSelectedOption().getText()) % 4 == 0 ? 29 : 28;
        int yearValue = Integer.parseInt(y.getFirstSelectedOption().getText());
        if (yearValue % 400 == 0 || (yearValue % 4 == 0 && yearValue % 100 != 0)) {
            fevDays = 29;
        } else {
            fevDays = 28;

        }
        for (int i = 0; i < 12; i++) {
            m.selectByIndex(i);
            if (months31.contains(m.getFirstSelectedOption().getText())) {
                Assert.assertEquals(d.getOptions().size(), 31);
            } else if (m.getFirstSelectedOption().getText().equals("February")) {
                Assert.assertEquals(d.getOptions().size(), fevDays);
            } else {
                Assert.assertEquals(d.getOptions().size(), 30);
            }
        }

    }

    @Test
    public void mainDepartments() {
        driver.get("https://www.amazon.com/");
        Assert.assertEquals(driver.findElement(By.className("nav-search-label")).getText(), "All");

        List<WebElement> l1 = new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();
        boolean notAlphabeticalOrder = false;
        for (int i = 0; i < l1.size() - 1; i++) {
            if (l1.get(i).getText().compareTo(l1.get(i + 1).getText()) > 0) {

                System.out.println("List is not sorted in alphabetical order!");
                notAlphabeticalOrder = true;
                break;

            }

        }
        Assert.assertTrue(notAlphabeticalOrder);
    }

    @Test
    public void links() {
        driver.get("https://www.amazon.com/gp/site-directory");
        WebElement element1 = driver.findElement(By.xpath("//option[contains(text(),'Clothing, Shoes & Jewelry')]"));
        System.out.println(element1.isDisplayed());
        WebElement element2 = driver.findElement(By.xpath("//option[contains(text(),'Sports & Outdoors')]"));
        System.out.println(element2.isDisplayed());
        WebElement element3 = driver.findElement(By.xpath("//h2[contains(text(),'Home, Garden & Tools')]"));
        System.out.println(element3.isDisplayed());
    }



    @Test
    public void validLinks() {
        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        for (WebElement each : elements){
            if (each.getAttribute("href")!=null){
                System.out.println("Valid");
            }else {
                System.out.println("not valid");
            }
        }

    }


    @Test
    public void cartAndPrime(){

        driver.get("https://amazon.com");
        WebElement spoon= driver.findElement(By.id("twotabsearchtextbox"));
        spoon.sendKeys("Wooden Spoon",Keys.ENTER );
        String randomSpoon = "Eddington 50002 Italian Olive Wood Cooking Spoon" ;
        driver.findElement(By.partialLinkText(randomSpoon)).click();

        WebElement quantity = driver.findElement(By.xpath("//span[@class='a-dropdown-prompt'][contains(text(),'1')]"));
        if (quantity.getText().contains("1")){
            System.out.println("Passed");
        }else{
            System.out.println("failed");
        }
//        BrowserUtils.wait(2);
        String expected = "Eddington 50002 Italian Olive Wood Cooking Spoon" ;
        Assert.assertEquals(randomSpoon,expected);
        System.out.println("String actual is : " + randomSpoon + " | When expected is : " + expected);

        WebElement cart =driver.findElement(By.id("add-to-cart-button"));
        System.out.println(cart.isDisplayed());



    }


    /**
     * PRIME
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click search
     * 4. remember name first result that has prime label
     * 5. select Prime checkbox on the left
     * 6. verify that name first result that has prime label is same as step 4
     * 7. check the last checkbox under Brand on the left
     * 8. verify that name first result that has prime label is different
     * <p>
     * MORE SPOONS
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. remember all Brand names on the left
     * 4. select Prime checkbox on the left
     * 5. verify that same Brand names are still displayed
     * CHEAP SPOONS
     * <p>
     * 1. go to https://amazon.com
     * 2. search for "wooden spoon"
     * 3. click on Price option Under $25 on the left
     * 4. verify that all results are cheaper than $25
     */

        @Test
        public void primeTest() throws InterruptedException {
            //4. remember name first result that has prime label
            // - get first item with prime label
            WebElement primeItem = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/ancestor::div[@data-component-type='s-search-result'])[1]"));
            // - get first item text
            //text is located in h2 tag
            String primeItemText = primeItem.findElement(By.tagName("h2")).getText().trim();
            System.out.println(primeItemText);
            //remember all Brand names on the left
            List<WebElement> brands = driver.findElements(By.cssSelector("#brandsRefinements a"));
            //select Prime checkbox on the left
            driver.findElement(By.xpath("//*[@aria-label='Prime Eligible']//a")).click();
            //verify that name first result that has prime label is same as step 4
            //find item again
            Thread.sleep(5000);
            primeItem = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/ancestor::div[@data-component-type='s-search-result'])[1]"));
            String afterClickingOnPrimeText = primeItem.findElement(By.tagName("h2")).getText().trim();
            Assert.assertEquals(afterClickingOnPrimeText, primeItemText);
            //check the last checkbox under Brand on the left
            brands = driver.findElements(By.cssSelector("#brandsRefinements a"));
            brands.get(brands.size() - 1).click();
            System.out.println(afterClickingOnPrimeText);
            // verify that name first result that has prime label is different
            primeItem = driver.findElement(By.xpath("(//i[@aria-label='Amazon Prime']/ancestor::div[@data-component-type='s-search-result'])[1]"));
            String afterClickingOnLastBrandTextOfLabel = primeItem.findElement(By.tagName("h2")).getText().trim();
            Assert.assertNotEquals(afterClickingOnPrimeText, afterClickingOnLastBrandTextOfLabel);
        }
        @Test
        public void moreSpoons() throws InterruptedException {
            //* 3. remember all Brand names on the left
            List<String> brandsBefore = driver.findElements(By.cssSelector("#brandsRefinements a")).stream().map(each -> each.getText().trim()).collect(Collectors.toList());
            //* 4. select Prime checkbox on the left
            driver.findElement(By.xpath("//*[@aria-label='Prime Eligible']//a")).click();
            Thread.sleep(5000);
            //* 5. verify that same Brand names are still displayed
            List<String> brandsAfter = driver.findElements(By.cssSelector("#brandsRefinements a")).stream().map(each -> each.getText().trim()).collect(Collectors.toList());//collect brands again
            for (int index = 0; index < brandsBefore.size(); index++) {
                //extract text
                String brandBefore = brandsBefore.get(index);
                String brandAfter = brandsAfter.get(index);
                //compare if text of every brand is the same as before clicking on Prime checkbox
                Assert.assertEquals(brandBefore, brandAfter);
            }
        }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


}

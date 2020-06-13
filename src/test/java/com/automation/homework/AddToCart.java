package com.automation.homework;

import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class AddToCart {
    private WebDriver driver = Driver.getDriver();
     public void test(){
         driver.get("https://amazon.com");
         driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
         List<WebElement> prices =driver.findElements(By.cssSelector("[class='a-plus'] > [class='a-offscreen']"));
         List<WebElement> descriptions = driver.findElements(By.cssSelector("[class= 'a-size-base-plus  a-color-base a-text-normal']"));


         System.out.println("Number of prices: "+ prices.size());
         System.out.println("Number of descriptions: "+descriptions.size());

         Random random= new Random();
         int randomNumber = random.nextInt(descriptions.size());

         WebElement randomPrice = prices.get(randomNumber);
         WebElement randomItem = descriptions.get(randomNumber);

         String expectedDescription= randomItem.getText().trim();
         String expectedPrice = randomPrice.getText().trim();

         randomItem.click(); //click on random item

         WebElement quantity = driver.findElement(By.xpath("//span[text()='Qty:']/following-sibling: :span"));

         int actual = Integer.parseInt(quantity.getText().trim());

         Assert.assertEquals(actual,1);

         WebElement productPrice = driver.findElement(By.id("productTitle"));
         WebElement productDescription =driver.findElement(By.id("[id='priceInsideBuyBox_feature_div'] > div"));

         String actualDescription = productDescription.getText().trim();
         String actualPrice =productPrice.getText().trim();

         Assert.assertEquals(expectedDescription,expectedPrice);
         Assert.assertEquals(actualDescription,actualPrice);

         driver.quit();

     }
}

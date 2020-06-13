package com.automation.homework;

import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

public class YearMonthsDaysLeap {
    private WebDriver driver = Driver.getDriver();

    @Test
    public void test(){
        driver.get("http://practice.cybertekschool.com/dropdown");


        Select year = new Select(driver.findElement(By.id("year")));
        Select months = new Select(driver.findElement(By.id("months")));
        Select days = new Select(driver.findElement(By.id("days")));


        Random random = new Random();

        int yearToSelect = random.nextInt(year.getOptions().size());


        //select a year
        year.selectByIndex(yearToSelect);

        for (int i = 0; i <12 ; i++) {
            //we create date object based on year and month
            LocalDate localDate = LocalDate.of(yearToSelect,1,1);

            //select a month
            months.selectByIndex(i);
            int actual = days.getOptions().size();//actual number of days
            int expected = Month.from(localDate).length(isLeapYear(yearToSelect)); //expected number of days in a month

            System.out.println("Month: "+ months.getFirstSelectedOption().getText());
            System.out.println("Expected number of days: "+expected);
            System.out.println("Actual number of days: "+actual);
            System.out.println();

//            Month.from(localDate).length(isLeapYear(yearToSelect));
            Assert.assertEquals(actual,expected);
        }
        driver.quit();


    }

    public static boolean isLeapYear(int year){
        if(year % 4 ==0){
            if(year % 100 ==0){
                return year % 400 ==0;

            }
        }
        return year % 400 ==0;


    }
}

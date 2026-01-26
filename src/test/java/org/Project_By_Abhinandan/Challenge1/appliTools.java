package org.Project_By_Abhinandan.Challenge1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class appliTools {
    @Test
    public void test_Total_Amount_of_Money_Spent()
    {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.applitools.com/");
        driver.manage().window().maximize();


        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("Admin");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(" Password@123");

        WebElement remember_me_checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        remember_me_checkbox.click();

        WebElement sign_in = driver.findElement(By.id("log-in"));
        sign_in.click();

        System.out.println(driver.getCurrentUrl());

        Assert.assertTrue(driver.getCurrentUrl().contains("app.html"));

        int totalTransaction = driver.findElements(By.xpath("//tbody//tr")).size();

        double earned=0.0;
        double spent =0.0;

        for(int i=1;i<=totalTransaction;i++)
        {
            String amount = driver.findElement(By.xpath("//tbody//tr["+i+"]//td[5]//span")).getText();
            if(amount.split(" ")[0].equalsIgnoreCase("+"))
            {
                earned = earned + Double.parseDouble(amount.split(" ")[1].replace(",",""));
            }
            if(amount.split(" ")[0].equalsIgnoreCase("-"))
            {
                spent = spent + Double.parseDouble(amount.split(" ")[1].replace(",",""));
            }

        }
        System.out.println("Earned = " + earned + "; Spend = " + spent + "; Balance = " +(float)(earned-spent));

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Assert.assertEquals(decimalFormat.format((float)(earned-spent)),"1996.22" );

        driver.quit();
    }
}

package org.Project_By_Abhinandan.Challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class test_modals {

    @Test
    public void test_modal()
    {
        // ---------- Browser Setup ----------
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/modal-dialogs");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;


        WebElement small_modal = wait.until(ExpectedConditions.elementToBeClickable(By.id("showSmallModal")));
        js.executeScript("arguments[0].scrollIntoView(true);", small_modal);
        js.executeScript("arguments[0].click();", small_modal);

        WebElement modal1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-body']")));
        System.out.println("Small modal text: " +modal1.getText());

        driver.findElement(By.xpath("//button[@id='closeSmallModal']")).click();

        WebElement large_modal = wait.until(ExpectedConditions.elementToBeClickable(By.id("showLargeModal")));
        js.executeScript("arguments[0].scrollIntoView(true);",large_modal);
        js.executeScript("arguments[0].click();", large_modal);

        WebElement modal2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-body']")));
        System.out.println("Large modal text: "+ modal2.getText());

        driver.findElement(By.xpath("//button[@id='closeLargeModal']")).click();

        driver.quit();


    }
}

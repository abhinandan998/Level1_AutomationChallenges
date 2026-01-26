package org.Project_By_Abhinandan.Challenge2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Web_tables_verification {

    @Test
    public void test_Web_tables_verification() {

        // ---------- Browser Setup ----------
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/webtables");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ---------- Test Data ----------
        String expFirstName = "Abhinandan";
        String expLastName  = "Basu";
        String expEmail     = "basuabhinandan99@gmail.com";
        String expAge       = "25";
        String expSalary    = "45480";
        String expDept      = "Development";

        // ---------- Click ADD button (JS click to avoid ads) ----------
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton"))
        );
        js.executeScript("arguments[0].scrollIntoView(true);", addBtn);
        js.executeScript("arguments[0].click();", addBtn);

        // ---------- Wait for Modal ----------
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));

        // ---------- Fill Form ----------
        driver.findElement(By.id("firstName")).sendKeys(expFirstName);
        driver.findElement(By.id("lastName")).sendKeys(expLastName);
        driver.findElement(By.id("userEmail")).sendKeys(expEmail);
        driver.findElement(By.id("age")).sendKeys(expAge);
        driver.findElement(By.id("salary")).sendKeys(expSalary);
        driver.findElement(By.id("department")).sendKeys(expDept);

        // ---------- Submit (JS click to bypass overlay) ----------
        WebElement submitBtn = driver.findElement(By.id("submit"));
        js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
        js.executeScript("arguments[0].click();", submitBtn);

        // ---------- Wait for Row to Appear ----------
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='rt-tr-group'][.//div[text()='" + expEmail + "']]")
        ));

        // ---------- Get All Cells ----------
        List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));

        // ---------- Assertions (Using JS textContent) ----------
        Assert.assertEquals(getTextJS(driver, cells.get(0)), expFirstName, "First Name mismatch");
        Assert.assertEquals(getTextJS(driver, cells.get(1)), expLastName, "Last Name mismatch");
        Assert.assertEquals(getTextJS(driver, cells.get(2)), expAge, "Age mismatch");
        Assert.assertEquals(getTextJS(driver, cells.get(3)), expEmail, "Email mismatch");
        Assert.assertEquals(getTextJS(driver, cells.get(4)), expSalary, "Salary mismatch");
        Assert.assertEquals(getTextJS(driver, cells.get(5)), expDept, "Department mismatch");

        System.out.println("✅ Record added and verified successfully");

        driver.quit();
    }

    // ---------- Utility Method for React Tables ----------
    public static String getTextJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].textContent;", element).toString().trim();
    }
}

package org.Project_By_Abhinandan.Challenge4;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class VwoLogin_and_DashboardPage {

    @Parameters("browsers")
    @Test
    public void test_vwo_login_and_dashboard_page(String browsers)
    {
        WebDriver driver;

        // 🔹 Browser selection
        if (browsers.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browsers.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name");
        }
        driver.manage().window().maximize();
        driver.get(" https://app.vwo.com/");


        WebElement username = driver.findElement(By.id("login-username"));
        username.sendKeys("txhtgr1581@supermmw.online");

        WebElement password = driver.findElement(By.id("login-password"));
        password.sendKeys("Abhi@12345");

        WebElement remember_me =driver.findElement(By.xpath("//span[@class='checkbox-radio-button ng-scope']"));
        remember_me.click();

        WebElement button = driver.findElement(By.id("js-login-btn"));
        button.click();

        WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement click_on_dashboard = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='Pt(18px) Bgc(--color-white)']/ul/li/div/a")));
        click_on_dashboard.click();

        WebElement visible_text= driver.findElement(By.xpath("//h4[contains(text(),'Dashboard')]"));

        Assert.assertEquals(visible_text.getText(), "Dashboard");



        WebElement user_menu =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@title='User menu'])[2]")));
        user_menu.click();

        WebElement log_out = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-qa='logout-btn']")));
        log_out.click();

        driver.quit();


    }

}

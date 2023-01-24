package Alerts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertTypes {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;

    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }
    @Test
    public void promptTest(){
        String prompt = "prompt('Input text: ')";
        js.executeScript(prompt);
        wait.until(ExpectedConditions.alertIsPresent());
        String promptText = driver.switchTo().alert().getText();
        System.out.println(promptText);
        driver.switchTo().alert().sendKeys("Test text");
        driver.switchTo().alert().accept();
        js.executeScript(prompt);
        driver.switchTo().alert().dismiss();
    }

}

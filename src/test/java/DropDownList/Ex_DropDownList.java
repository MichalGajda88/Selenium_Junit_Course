package DropDownList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ex_DropDownList {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("web.driver.chromedriver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void
}

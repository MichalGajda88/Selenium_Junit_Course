package Frames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Frames_navigation {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.nasa.gov/topics/earth/index.html");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void framesNaviTest() {
        WebElement twitterFrame = driver.findElement(By.cssSelector("iframe[id='twitter-widget-0']"));
        driver.switchTo().frame(twitterFrame);
        WebElement viewTwitter = driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-6koalj r-18u37iz r-1777fci']"));
        viewTwitter.click();
        driver.switchTo().defaultContent();
        WebElement logo = driver.findElement(By.cssSelector("div[id='navbar'] a[title='Home Page']"));

        Assertions.assertTrue(logo.isDisplayed(), "");
    }
}

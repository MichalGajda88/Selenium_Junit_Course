package Frames;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Frames_basics {
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
    public void framesBasics() {
        WebElement twitterFrame = driver.findElement(By.cssSelector("iframe[id='twitter-widget-0']"));
        driver.switchTo().frame(twitterFrame);
        WebElement viewTwitter = driver.findElement(By.cssSelector("a[class='css-4rbku5 css-18t94o4 css-1dbjc4n r-l5o3uw r-sdzlij r-1phboty r-rs99b7 r-1loqt21 r-2yi16 r-1qi8awa r-1ny4l3l r-ymttw5 r-o7ynqc r-6416eg r-lrvibr']"));
        viewTwitter.click();
        driver.switchTo().defaultContent();
    }
}

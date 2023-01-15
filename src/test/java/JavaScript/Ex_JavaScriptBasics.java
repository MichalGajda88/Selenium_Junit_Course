package JavaScript;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ex_JavaScriptBasics {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/product/zmien-swoja-sylwetke-yoga-na-malcie/");
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement startElement = driver.findElement(By.cssSelector("li[id='tab-title-description']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", startElement);
        WebElement addToCartBar = driver.findElement(By.cssSelector("div[class='storefront-sticky-add-to-cart__content']"));
        Assertions.assertTrue(addToCartBar.isDisplayed(), "Web element: " + addToCartBar + "is missing");
    }
}

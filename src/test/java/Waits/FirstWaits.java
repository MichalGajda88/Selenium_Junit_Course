package Waits;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FirstWaits {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(100000), Duration.ofSeconds(5));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/");
    }

    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void waitTest(){
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("section[class='storefront-product-section storefront-recent-products'] a[data-product_id='391']"));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton)).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a[class='added_to_cart wc-forward']")))).click();

        WebElement inputQuantity = driver.findElement(By.cssSelector("input[class='input-text qty text']"));
        wait.until(ExpectedConditions.visibilityOf(inputQuantity)).sendKeys("2");
    }
}

package WaitsAndGettingInfo;

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

public class FirstWaits {
    WebDriver driver;
    WebDriverWait wait;
    String expectedPrice,
            currentPrice;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void waitTest() {
        driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']")).click();

        driver.findElement(By.cssSelector("section[class*='recent'] li[class*='post-391']  a[class*='loop']")).click();
        driver.findElement(By.cssSelector("button[class='single_add_to_cart_button button alt']")).click();

        driver.findElement(By.cssSelector("div[role='alert'] a[class='button wc-forward']")).click();
        WebElement inputQuantity = driver.findElement(By.cssSelector("div[class='quantity'] input[class*='input-text']"));
        inputQuantity.clear();
        inputQuantity.sendKeys("2");
        driver.findElement(By.cssSelector("button[name='update_cart']")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class*='blockUI']"), 0));

        expectedPrice = "6 400,00" + " zł";
        currentPrice = driver.findElement(By.cssSelector("tr[class='order-total'] strong")).getText();

        Assertions.assertEquals(expectedPrice, currentPrice, "Total price: " + currentPrice + " is different thann expected: "
                + expectedPrice);
    }
}

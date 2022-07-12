package Waits;

import org.jetbrains.annotations.NotNull;
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

public class Ex_ExpectedConditions {
    WebDriver driver;
    WebDriverWait wait;
    String correctCoupon = "10procent",
            wrongCoupon = "12321",
            expectedEmptyCouponMessage = "Proszę wpisać kod kuponu.",
            expectedCorrectCouponMessage = "Kupon został pomyślnie użyty.",
            expectedWrongCouponMessage = "Kupon \"" + wrongCoupon + "\" nie istnieje!",
            expectedUsedCouponMessage = "Kupon został zastosowany!",
            currentMessage;

    public void enterCouponAndWait(@NotNull WebDriver driver, @NotNull WebDriverWait wait, String value) {
        WebElement couponInput = driver.findElement(By.cssSelector("input[id='coupon_code']"));
        couponInput.clear();
        couponInput.sendKeys(value);
        driver.findElement(By.cssSelector("button[name='apply_coupon']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class*='blockOverlay']"), 0));
    }

    public String getAlertText() {
        currentMessage = driver.findElement(By.cssSelector("ul[class='woocommerce-error']")).getText();
        return currentMessage;
    }

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        driver.findElement(By.cssSelector("img[alt='Wspinaczka']")).click();
        driver.findElement(By.cssSelector("li[class*='post-42'] img")).click();
        driver.findElement(By.cssSelector("button[class*='single_add_to_cart_button']")).click();
        driver.findElement(By.cssSelector("div[role='alert'] a[class='button wc-forward']")).click();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void correctValue() {
        enterCouponAndWait(driver, wait, correctCoupon);
        Assertions.assertEquals(expectedCorrectCouponMessage, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void wrongValue(){
        enterCouponAndWait(driver, wait, wrongCoupon);
        Assertions.assertEquals(expectedWrongCouponMessage, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void emptyValue(){
        enterCouponAndWait(driver, wait, "");
        Assertions.assertEquals(expectedEmptyCouponMessage, getAlertText(), "Alert text is different than expected one");
    }

}

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
            expectedEmptyCouponAlert = "Proszę wpisać kod kuponu.",
            expectedCorrectCouponAlert = "Kupon został pomyślnie użyty.",
            expectedWrongCouponAlert = "Kupon \"" + wrongCoupon + "\" nie istnieje!",
            expectedUsedCouponAlert = "Kupon został zastosowany!",
            expectedRemovedCouponAlert = "Kupon został usunięty.";

    public void enterCouponAndWait(String value) {
        WebElement couponInput = driver.findElement(By.cssSelector("input[id='coupon_code']"));
        couponInput.clear();
        couponInput.sendKeys(value);
        driver.findElement(By.cssSelector("button[name='apply_coupon']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class*='blockOverlay']"), 0));
    }

    public void removeCouponAndWait() {
        driver.findElement(By.cssSelector("a[class='woocommerce-remove-coupon']")).click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class*='blockOverlay']"), 0));
    }

    public String getAlertText() {
        By currentAlert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentAlert)).getText();
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
        enterCouponAndWait(correctCoupon);
        Assertions.assertEquals(expectedCorrectCouponAlert, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void usedValue() {
        int i = 0;
        while (i < 2) {
            enterCouponAndWait(correctCoupon);
            i++;
        }
        Assertions.assertEquals(expectedUsedCouponAlert, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void wrongValue() {
        enterCouponAndWait(wrongCoupon);
        Assertions.assertEquals(expectedWrongCouponAlert, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void emptyValue() {
        enterCouponAndWait("");
        Assertions.assertEquals(expectedEmptyCouponAlert, getAlertText(), "Alert text is different than expected one");
    }

    @Test
    public void removeCoupon() {
        correctValue();
        removeCouponAndWait();
        Assertions.assertEquals(expectedRemovedCouponAlert, getAlertText(), "Alert text is different than expected one");
    }
}

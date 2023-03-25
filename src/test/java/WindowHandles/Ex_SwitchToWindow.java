package WindowHandles;

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
import java.util.Set;

public class Ex_SwitchToWindow {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/");
        WebElement dismissLink = driver.findElement(By.cssSelector("a[class='woocommerce-store-notice__dismiss-link']"));
        dismissLink.click();
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }
    @Test
    public void windowHandleExercise(){
        WebElement windsurfing = driver.findElement(By.cssSelector("img[alt='Windsurfing']"));
        windsurfing.click();
        WebElement fuerteventura = driver.findElement(By.xpath(".//h2[text()='Fuerteventura – Sotavento']"));
        fuerteventura.click();
        WebElement addToWishlistButton = driver.findElement(By.cssSelector("a[class='add_to_wishlist single_add_to_wishlist']"));
        addToWishlistButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class='blockUI blockOverlay']"),0));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class='yith-wcwl-wishlistaddedbrowse']"))));
        WebElement wishlistButton = driver.findElement(By.cssSelector("li[id='menu-item-248']"));
        wishlistButton.click();
        Set<String> windows = driver.getWindowHandles();
        String productPage = driver.getWindowHandle();
        windows.remove(productPage);
        String wishlistPage = windows.iterator().next();
        driver.switchTo().window(wishlistPage);
        WebElement removeFromWishlistButton = driver.findElement(By.cssSelector("a[class='remove remove_from_wishlist']"));
        removeFromWishlistButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("div[class='blockUI blockOverlay']"),0));
        WebElement alert = driver.findElement(By.cssSelector("div[role='alert']"));
        WebElement wishlistEmpty = driver.findElement(By.cssSelector("td[class='wishlist-empty']"));
        Assertions.assertTrue(alert.isDisplayed(),"The product removal notification is not displayed");
        Assertions.assertTrue(wishlistEmpty.isDisplayed(),"The wishlist is not empty");
    }
}

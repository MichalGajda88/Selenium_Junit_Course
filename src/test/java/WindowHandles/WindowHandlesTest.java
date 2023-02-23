package WindowHandles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WindowHandlesTest {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.navigate().to("https://testelka.pl/blog/");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        WebElement cookieAcceptButton = driver.findElement(By.cssSelector("a[id='cookie_action_close_header']"));
        cookieAcceptButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieAcceptButton));
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }
    @Test
    public void windowHandles(){
        WebElement ytButton = driver.findElement(By.cssSelector("span[class='dashicons dashicons-video-alt3']"));
        js.executeScript("arguments[0].scrollIntoView(true)", ytButton);
        ytButton.click();
        Set<String> windows = driver.getWindowHandles();
        String blogWindow = driver.getWindowHandle();
        windows.remove(blogWindow);
        String ytWindow = windows.iterator().next();
        driver.switchTo().window(ytWindow);
        WebElement policyAcceptButton = driver.findElement(By.xpath(".//span[text()='Zaakceptuj wszystko']"));
        policyAcceptButton.click();
        wait.until(ExpectedConditions.invisibilityOf(policyAcceptButton));
        WebElement ytLogo = driver.findElement(By.cssSelector(".style-scope ytd-logo"));
        Assertions.assertTrue(ytLogo.isDisplayed(),"YT logo is missing.");
        driver.switchTo().window(blogWindow);
    }

}

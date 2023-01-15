package JavaScript;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptBasics {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void javaScriptExample() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('cośtam');");
        String domainName = (String) js.executeScript("return document.domain");
    }

    @Test
    public void javaAsyncScript() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }

    @Test
    public void javaSyncScript() {
        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeScript("window.setTimeout(arguments[arguments.length - 1], 500);");
        long elapsedTime = System.currentTimeMillis() - start;
        System.out.println("Elapsed time: " + elapsedTime);
    }
}

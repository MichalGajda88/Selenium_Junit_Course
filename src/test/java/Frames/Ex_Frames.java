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

public class Ex_Frames {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void frameContentTest() {
        driver.switchTo().frame("main-frame");
        //1. Potwierdź, że pierwszy przycisk „Strona główna” jest nieaktywny.
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        Assertions.assertFalse(mainPageButton.isEnabled(), "State of main page button is different than expected one");

        //2. Potwierdź, że obrazek kieruje do strony głównej (sprawdź bez klikania w element).
        driver.switchTo().frame("image");
        String mainPageAddress = "https://fakestore.testelka.pl/";
        WebElement mainPageImage = driver.findElement(By.cssSelector("img[alt='Wakacje']"));
    }
}

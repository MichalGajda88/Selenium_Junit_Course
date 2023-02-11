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
    //1. Potwierdź, że pierwszy przycisk „Strona główna” jest nieaktywny.

    @Test
    public void mainPageButtonIsDisabled() {
        driver.switchTo().frame("main-frame");
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        Assertions.assertFalse(mainPageButton.isEnabled(), "State of main page button is different than expected one");
    }

    //2. Potwierdź, że obrazek kieruje do strony głównej (sprawdź bez klikania w element).
    @Test
    public void imageRedirectToMainPage() {
        driver.switchTo().frame("main-frame");
        WebElement frame = driver.findElement(By.cssSelector("iframe[name='image']"));
        driver.switchTo().frame("image");
        String mainPageAddress = "https://fakestore.testelka.pl/";
        WebElement mainPageImageLink = driver.findElement(By.xpath(".//img[@alt='Wakacje']/.."));
        Assertions.assertEquals(mainPageAddress, mainPageImageLink.getAttribute("href"),
                "Link address is different than expected one.");
    }

    @Test
    public void mainPageButtonIsEnabled() {
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame("image");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[loading='lazy']")));
        WebElement mainPageButton = driver.findElement(By.xpath(".//a[text()='>> Strona główna' and @class='button']"));
        Assertions.assertTrue(mainPageButton.isEnabled(), "Main page button is inactive");
    }
    //4. Kliknij w przycisk „Wspinaczka” (po wcześniejszym kliknięciu w przycisk „>>Strona główna”)  i potwierdź, że po
    // przejściu na stronę widoczne jest logo (w tej ramce).
    @Test
    public void logoIsDisplayed(){
        driver.switchTo().frame("main-frame");
        driver.switchTo().frame("image");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[loading='lazy']")));
        WebElement mainPageButton = driver.findElement(By.xpath(".//a[text()='>> Strona główna' and @class='button']"));
        mainPageButton.click();
        driver.switchTo().frame("main-frame");
        WebElement climbingButton = driver.findElement(By.cssSelector("a[value='wspinaczka']"));
        climbingButton.click();
        WebElement logo = driver.findElement(By.xpath(".//a[@class='custom-logo-link']//img"));
    }
}

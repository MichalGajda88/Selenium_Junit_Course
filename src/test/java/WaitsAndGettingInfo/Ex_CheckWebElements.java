package WaitsAndGettingInfo;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Ex_CheckWebElements {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/metody-na-elementach/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void isActive() {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[type='button']"));
        assertFalse(mainPageButton.isEnabled(), "State of button is different than expected one");
    }

    @Test
    public void isVisible() {
        WebElement sailingButton = driver.findElement(By.cssSelector("a[name='sailing']"));
        assertFalse(sailingButton.isDisplayed(), "State of button is different than expected one");
    }

    @Test
    public void checkButtonColour() {
        WebElement climbingButton = driver.findElement(By.cssSelector("a[name='climbing']"));
        WebElement windsurfingButton = driver.findElement(By.cssSelector("a[name='windsurfing']"));
        WebElement yogaButton = driver.findElement(By.cssSelector("a[name='yoga']"));

        assertAll("colour",
                () -> assertEquals("rgba(245, 233, 101, 1)", climbingButton.getCssValue("background-color")),
                () -> assertEquals("rgba(245, 233, 101, 1)", windsurfingButton.getCssValue("background-color")),
                () -> assertEquals("rgba(245, 233, 101, 1)", yogaButton.getCssValue("background-color"))
        );
    }

    @Test
    public void isChecked() {
        WebElement checkedCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement uncheckedCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement checkedRadio = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement uncheckedRadio = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));

        assertAll("isChecked",
                () -> assertEquals("true", checkedCheckbox.getAttribute("checked")),
                () -> assertNull(uncheckedCheckbox.getAttribute("checked")),
                () -> assertEquals("true", checkedRadio.getAttribute("checked")),
                () -> assertNull(uncheckedRadio.getAttribute("checked"))
        );
    }

    @Test
    public void checkTag() {
        List<WebElement> buttonList = driver.findElements(By.className("button"));
        for (int i=0; i<buttonList.size(); i++){
            Assertions.assertEquals("a", buttonList.get(i).getTagName(), "Button tag name is different than expected one");
        }
    }
}

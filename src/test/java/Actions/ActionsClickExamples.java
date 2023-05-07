package Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ActionsClickExamples {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void actionsClick() {
        driver.navigate().to("http://jqueryui.com/selectable/#default");
        //   actions.moveByOffset(937,369).click().build().perform();
        driver.switchTo().frame(0);
        List<WebElement> itemList = driver.findElements(By.cssSelector("#selectable>li"));
        WebElement firstElement = itemList.get(0);
        actions.click(firstElement);
    }

    @Test
    public void doubleClickExample() {
        driver.navigate().to("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        // actions.moveByOffset(84,137).doubleClick().build().perform();
        WebElement box = driver.findElement(By.cssSelector("#box"));
        actions.doubleClick(box).build().perform();
    }

    @Test
    public void contextClickExample() {
        driver.navigate().to("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        //  actions.moveByOffset(391, 196).contextClick().build().perform();
        WebElement button = driver.findElement(By.cssSelector(".context-menu-one"));
        actions.contextClick(button).build().perform();
    }

    @Test
    public void sendKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        WebElement userNameField = driver.findElement(By.cssSelector("#username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", userNameField);
        actions.sendKeys(userNameField, Keys.SHIFT, "test_login").build().perform();
    }

    @Test
    public void holdKeysExample() {
        driver.navigate().to("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        WebElement userNameField = driver.findElement(By.cssSelector("#username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", userNameField);
        actions.keyDown(Keys.SHIFT).sendKeys(userNameField, "Test_login").keyUp(Keys.SHIFT).build().perform();
    }

    @Test
    public void holdKeysToSelect() {
        driver.navigate().to("https://jqueryui.com/selectable/#default");
        driver.switchTo().frame(0);
        List<WebElement> items = driver.findElements(By.cssSelector("li.ui-selectee"));
        actions.keyDown(Keys.CONTROL)
                .click(items.get(0))
                .click(items.get(1))
                .click(items.get(2))
                .keyUp(Keys.CONTROL)
                .click(items.get(3))
                .build().perform();
    }
}

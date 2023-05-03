package Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}

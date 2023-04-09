package Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsClickTest {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("http://jqueryui.com/selectable/#default");
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }
    @Test
    public void actionsClick(){
        actions = new Actions(driver);
        actions.moveByOffset(937,369).click().build().perform();
    }
}

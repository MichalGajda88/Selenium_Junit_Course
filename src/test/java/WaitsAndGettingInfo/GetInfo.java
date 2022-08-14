package WaitsAndGettingInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetInfo {

    WebDriver driver;
    WebElement header;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/");
    }

    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void getElementInfo(){
    header = driver.findElement(By.cssSelector("[id='masthead']"));
    String text = header.getText();
    String attribute = header.getAttribute("role");
    String cssValue = header.getCssValue("color");
    Dimension size = header.getSize();
    String tagName = header.getTagName();
    Point location = header.getLocation();
    Rectangle locationAndPosition = header.getRect();
    Boolean isDisplayed = header.isDisplayed();
    Boolean isSelected = header.isSelected();
    Boolean isEnabled = header.isEnabled();
    }
}

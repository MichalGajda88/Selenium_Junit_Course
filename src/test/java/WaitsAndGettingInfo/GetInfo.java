package WaitsAndGettingInfo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    }
}

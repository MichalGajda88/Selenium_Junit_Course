package DropDownList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropDownListTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.allegro.pl/");
        Cookie permission = new Cookie("gdpr_permission_given", "1");
        driver.manage().addCookie(permission);
        driver.manage().window().
    }
    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void dropDownList(){

    }

}

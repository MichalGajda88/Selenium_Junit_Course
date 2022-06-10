package Interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CheckLogin {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("https://www.zooniverse.org/");
    }

    @AfterEach
    public void driverQuit(){
     driver.quit();
    }

    @Test
    public void activeButtonTest(){
        driver.findElement(By.cssSelector("span[class='login-bar'] > button[value='sign-in']")).click();

        driver.findElement(By.cssSelector("input[name='login']")).sendKeys("biosys");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("biosys1234");
        driver.findElement(By.cssSelector("form[method='POST']")).submit();

        String login = driver.findElement(By.cssSelector("span[class='site-nav__link'] > strong")).getText().toLowerCase(Locale.ROOT);

        Assertions.assertEquals("biosys", login, "Incorrect login");
    }
}

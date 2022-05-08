package FirstSteps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.GregorianCalendar;
import java.util.Set;

public class CookiesTest {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setPosition(new Point(10, 100));
        driver.get("https://www.onet.pl/");
    }

    @AfterEach
    public void driverQuit() {
        driver.close();
        driver.quit();
    }


    @Test
    public void getDeleteCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(22, driver.manage().getCookies().size(), "Invalid number of cookies");

        Cookie cookie1 = driver.manage().getCookieNamed("acc_segment");
        driver.manage().deleteCookie(cookie1);
        Assertions.assertEquals(21, driver.manage().getCookies().size(), "Cookie not deleted");

        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size(), "Cookies not deleted");
    }

    @Test
    public void addCookies() {
        Cookie testCookie = new Cookie("test_name", "test_value", ".onet.pl", "/", new GregorianCalendar(2022, 5, 3).getTime(), true, true);
        driver.manage().addCookie(testCookie);
        Assertions.assertEquals(testCookie, driver.manage().getCookieNamed("test_name"), "Test cookie not found");
    }
}

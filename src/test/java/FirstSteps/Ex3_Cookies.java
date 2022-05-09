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

public class Ex3_Cookies {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().setPosition(new Point(30, 30));
        driver.get("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
        Thread.sleep(1500);
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    // point 1
    @Test
    public void getAndCheckCookies() {
        Assertions.assertEquals(7, driver.manage().getCookies().size(), "Wrong number of cookies");
    }

    // points 2, 3, 4
    @Test
    public void addAndDeleteCookie() {
        Cookie test_cookie = new Cookie("test_name", "test_value", "pl.wikipedia.org", "/",
                new GregorianCalendar(2022, 6, 2).getTime(), true, true);

        driver.manage().addCookie(test_cookie);
        Assertions.assertEquals(test_cookie.getName(), driver.manage().getCookieNamed("test_name").getName(),
                "Cookie not found");
        Assertions.assertEquals(8, driver.manage().getCookies().size(), "Wrong number of cookies");

        driver.manage().deleteCookie(test_cookie);
        Assertions.assertEquals(7, driver.manage().getCookies().size(), "Cookie not deleted");
    }

    //point 5
    @Test
    public void deleteCookieNamed(){
        driver.manage().deleteCookieNamed("GeoIP");
        Assertions.assertNull(driver.manage().getCookieNamed("GeoIP"), "Cookie not deleted");
    }

    //point 6
    @Test
    public void getCookieAndCheckAtributes(){
       Cookie wikiCookie = driver.manage().getCookieNamed("WMF-Last-Access");

       Assertions.assertEquals("pl.wikipedia.org", wikiCookie.getDomain(), "Domain incorrect");
       Assertions.assertEquals("/", wikiCookie.getPath(), "Path incorrect");
       Assertions.assertTrue(wikiCookie.isHttpOnly(), "HTTP flag incorrect");
    }
}

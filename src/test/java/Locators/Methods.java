package Locators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Methods {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findById() {
        driver.findElement(By.id("searchInput"));
    }

    @Test
    public void findByName() {
        driver.findElement(By.name("search"));
    }

    @Test
    public void findByClass() {
        driver.findElement(By.className("vector-search-box-input"));
    }

    @Test
    public void findElementsByClass() {
        List<WebElement> extiwClassElements = driver.findElements(By.className("extiw"));
        WebElement sameClassName = null;

        for (WebElement extiwClassElement : extiwClassElements) {
            String elementClass = extiwClassElement.getAttribute("class");

            if (elementClass.equals("extiw")) {
                sameClassName = extiwClassElement;
            }
        }
        Assertions.assertTrue(sameClassName != null, "Element was not found");
    }

}

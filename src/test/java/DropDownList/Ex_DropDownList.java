package DropDownList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ex_DropDownList {
    WebDriver driver;
    WebDriverWait wait;
    String lowestPrice = "2 900,00 zł",
            highestPrice = "5 399,00 zł";
    WebElement sortList,
            category;
    String firstPrice, lastPrice;

    Select sortOption;
    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/");

        WebElement cookieButton = driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link"));
        cookieButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieButton));

        category = driver.findElement(By.cssSelector("li[class='product-category product first']"));
        category.click();
        sortList = driver.findElements(By.cssSelector("select[class='orderby']")).get(0);
        sortOption = new Select(sortList);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void sortByPriceAsc() {
        sortOption.selectByValue("price");
        firstPrice = driver.findElement(By.xpath(".//ul[@class='products columns-3']/li[1]/a/span/span")).getText();
        lastPrice = driver.findElement(By.xpath(".//ul[@class='products columns-3']/li[6]/a/span/span")).getText();
        Assertions.assertEquals(lowestPrice, firstPrice, "First price on list is not the lowest");
        Assertions.assertEquals(highestPrice, lastPrice, "Last price on the list is not the highest");
    }

    @Test
    public void sortByPriceDesc() {
        sortOption.selectByValue("price-desc");
        firstPrice = driver.findElement(By.xpath(".//ul[@class='products columns-3']/li[1]/a/span/span")).getText();
        lastPrice = driver.findElement(By.xpath(".//ul[@class='products columns-3']/li[6]/a/span/span")).getText();
        Assertions.assertEquals(highestPrice, firstPrice, "First price on list is not the highest");
        Assertions.assertEquals(lowestPrice, lastPrice, "Last price on list is not the lowest");
    }
}

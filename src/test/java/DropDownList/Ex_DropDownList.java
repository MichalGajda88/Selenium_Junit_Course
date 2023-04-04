package DropDownList;

import org.junit.jupiter.api.AfterEach;
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

    @BeforeEach
    public void driverSetup() {
        System.setProperty("web.driver.chromedriver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/");
        WebElement cookieButton = driver.findElement(By.cssSelector("a.woocommerce-store-notice__dismiss-link"));
        cookieButton.click();
        wait.until(ExpectedConditions.invisibilityOf(cookieButton));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void sortByPriceAsc() {
        WebElement category = driver.findElement(By.cssSelector("li[class='product-category product first']"));
        category.click();
        WebElement sortList = driver.findElement(By.cssSelector("select[class='orderby']"));
        Select sortOption = new Select(sortList);
        sortOption.selectByValue("price");
        WebElement firstPrice = driver.findElement(By.xpath(".//ul[@class='products columns-3']/li[1]/a/span/span"));
        WebElement lastPrice
    }
}

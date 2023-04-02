package DropDownList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropDownListTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void dropDownList() {
        driver.navigate().to("https://www.allegro.pl/");
        WebElement alert = driver.findElement(By.cssSelector("div[role='alertdialog']"));
        Cookie permission = new Cookie("gdpr_permission_given", "1");
        driver.manage().addCookie(permission);
        driver.navigate().refresh();
        wait.until(ExpectedConditions.invisibilityOf(alert));

        WebElement productCategoriesList = driver.findElement(By.cssSelector("select[data-role='filters-dropdown-toggle']"));
        Select productCategory = new Select(productCategoriesList);
        productCategory.selectByIndex(3);
        productCategory.selectByValue("/kategoria/elektronika");
        productCategory.selectByVisibleText("Firma i usługi");
        Boolean isMultiple = productCategory.isMultiple();
        List<WebElement> options = productCategory.getOptions();
        List<WebElement> allSelectedOptions = productCategory.getAllSelectedOptions();
        WebElement firstSelectedElement = productCategory.getFirstSelectedOption();
    }

    @Test
    public void multiSelectDropDownList() {
        driver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        WebElement acceptButton = driver.findElement(By.cssSelector("div[id='accept-choices']"));
        acceptButton.click();
        wait.until(ExpectedConditions.invisibilityOf(acceptButton));

        WebElement iframe = driver.findElement(By.cssSelector("iframe[id='iframeResult']"));
        driver.switchTo().frame(iframe);
        WebElement carList = driver.findElement(By.cssSelector("select[id='cars']"));
        Select carSelect = new Select(carList);
        carSelect.selectByValue("volvo");
        carSelect.selectByValue("saab");
        carSelect.deselectByIndex(1);
        Boolean isMultiple = carSelect.isMultiple();
        carSelect.selectByIndex(2);
        carSelect.selectByIndex(3);
        carSelect.deselectAll();
    }
}

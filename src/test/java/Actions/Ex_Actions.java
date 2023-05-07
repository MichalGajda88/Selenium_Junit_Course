package Actions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class Ex_Actions {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        driver.manage().addCookie(new Cookie("store_noticef7db772cd2958546f5ffc7e2822d64e8", "hidden"));
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void shoppingCartInContext() {
        WebElement menu = driver.findElement(By.cssSelector("#menu-link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", menu);
        actions.contextClick(menu).build().perform();
        List<WebElement> contextMenuList = driver.findElements(By.cssSelector("#div-context-menu>ul>li"));
        WebElement cart = contextMenuList.get(2);
        actions.click(cart).build().perform();
        Assertions.assertEquals(driver.getCurrentUrl(), "https://fakestore.testelka.pl/koszyk/",
                "Actual page is not expected one");
    }

    @Test
    public void checkSquareColor() {
        WebElement square = driver.findElement(By.cssSelector("#double-click"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", square);
        actions.doubleClick(square).build().perform();
        Assertions.assertEquals(square.getCssValue("background-color"), "rgba(245, 93, 122, 1)",
                "Square color is different than expected one.");
    }

    @Test
    public void inputText() {
        WebElement input = driver.findElement(By.cssSelector("#input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", input);
        String inputText = "Test";
        actions.sendKeys(input, inputText).build().perform();
        WebElement acceptButton = driver.findElement(By.cssSelector("button[onclick='zatwierdzTekst()']"));
        actions.click(acceptButton).build().perform();
        WebElement output = driver.findElement(By.cssSelector("#output"));
        Assertions.assertEquals(output.getText(), "Wprowadzony tekst: " + inputText,
                "Output is different than expected one");
    }

    @Test
    public void selectSquares() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",
                driver.findElement(By.cssSelector("#selectable")));
        List<WebElement> squares = driver.findElements(By.cssSelector("#selectable>li"));
        actions.keyDown(Keys.CONTROL)
                .click(squares.get(1))
                .click(squares.get(2))
                .click(squares.get(3))
                .keyUp(Keys.CONTROL)
                .build().perform();
        Assertions.assertEquals(squares.get(1).getAttribute("class"), "ui-state-default ui-selectee ui-selected",
                "Square is not selected");
        Assertions.assertEquals(squares.get(2).getAttribute("class"), "ui-state-default ui-selectee ui-selected",
                "Square is not selected");
    }
}

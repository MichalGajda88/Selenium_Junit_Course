package Alerts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Ex_Alerts {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    String buttonConfirm = "button[onclick='confirmFunction()']";
    String buttonPrompt = "button[onclick='promptFunction()']";
    String userName = "Mietek Szcześniak";
    String acceptConfirm = "Wybrana opcja to OK!";
    String cancelConfirm = "Wybrana opcja to Cancel!";
    String promptConfirm = "Cześć "+ userName+"! Jak leci?";
    String promptCancel = "Użytkownik anulował akcję.";

    public void clickAndWaitForAlert(String button) {
        WebElement confirmButton = driver.findElement(By.cssSelector(button));
        confirmButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
    }

    @BeforeEach
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.navigate().to("https://jsfiddle.net/nm134se7/");
        WebElement frame = driver.findElement(By.cssSelector("iframe[name='result']"));
        driver.switchTo().frame(frame);
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void confirmAcceptation() {
        clickAndWaitForAlert(buttonConfirm);
        driver.switchTo().alert().accept();
        WebElement confirmResultText = driver.findElement(By.cssSelector("p[id='demo']"));
        Assertions.assertEquals(acceptConfirm, confirmResultText.getText(), "Text is different than expected one.");
    }

    @Test
    public void confirmCancellation() {
        clickAndWaitForAlert(buttonConfirm);
        driver.switchTo().alert().dismiss();
        WebElement confirmResultText = driver.findElement(By.cssSelector("p[id='demo']"));
        Assertions.assertEquals(cancelConfirm, confirmResultText.getText(), "Text is different than expected one.");
    }

    @Test
    public void promptAcceptation() {
        clickAndWaitForAlert(buttonPrompt);
        driver.switchTo().alert().sendKeys(userName);
        driver.switchTo().alert().accept();
        WebElement confirmPromptText = driver.findElement(By.cssSelector("p[id='prompt-demo']"));
        Assertions.assertEquals(promptConfirm, confirmPromptText.getText(),"Text is different than expected one.");
    }
    @Test
    public void promptCancellation(){
        clickAndWaitForAlert(buttonPrompt);
        driver.switchTo().alert().dismiss();
        WebElement confirmPromptText = driver.findElement(By.cssSelector("p[id='prompt-demo']"));
        Assertions.assertEquals(promptCancel, confirmPromptText.getText(),"Text is different than expected one.");
    }
}

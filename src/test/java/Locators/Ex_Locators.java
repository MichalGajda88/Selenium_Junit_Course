package Locators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Ex_Locators {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    //element #1 "szukajka w prawym górnym rogu"
    @Test
    public void findSearchField() {
        driver.findElement(By.id("woocommerce-product-search-field-0"));
        driver.findElement(By.className("search-field"));
        driver.findElement(By.name("s"));
    }

    //element #2 "pole do wpisania nazwy użytkownika"
    @Test
    public void findUsersLoginField() {
        driver.findElement(By.id("username"));
        driver.findElement(By.name("username"));
    }

    //element #3 "pole do wpisania hasła"
    @Test
    public void findUsersPasswordField() {
        driver.findElement(By.id("password"));
        driver.findElement(By.name("password"));
    }

    //element #4 "przycisk logowania"
    @Test
    public void findLoginButton() {
        driver.findElement(By.name("login"));
    }

    //element #5 "checkbox do zapamiętania hasła"
    @Test
    public void findRememberMeCheckbox() {
        driver.findElement(By.name("rememberme"));
        driver.findElement(By.id("rememberme"));
    }

    //element #6 "link do odzyskiwania hasła"
    @Test
    public void findPasswordReminderLink() {
        driver.findElement(By.linkText("Nie pamiętasz hasła?"));
        driver.findElement(By.partialLinkText("pamiętasz"));
    }

    //element #7 "link do kategorii “Żeglarstwo”"
    @Test
    public void findSailingLink() {
        driver.findElement(By.linkText("Żeglarstwo"));
        driver.findElement(By.partialLinkText("Żegl"));
    }
}


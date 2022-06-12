package Interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Ex_LoginTest {
    WebDriver driver;
    String correctName = "biosysit",
            correctPassword = "biosys1234",
            incorrectValue = "abcd1234",
            emailDomain = "@gmail.com";

    String expectedIncorrectEmailAlert = "Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.",
            expectedIncorrectNameAlert = "Błąd: Brak " + incorrectValue +" wśród zarejestrowanych w witrynie użytkowników. " +
                    "Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.",
            expectedEmptyNameAlert = "Błąd: Nazwa użytkownika jest wymagana.",
            expectedIncorrectPasswordForEmailAlert = "Błąd: Dla adresu e-mail " + correctName + emailDomain +
                    " podano nieprawidłowe hasło. Nie pamiętasz hasła?",
            expectedIncorrectPasswordForNameAlert = "Błąd: Wprowadzone hasło dla użytkownika biosysit jest niepoprawne. Nie pamiętasz hasła?",
            expectedEmptyPasswordAlert = "Błąd: Hasło jest puste.";

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void correctEmailCorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(correctName + emailDomain);
        passwordField.sendKeys(correctPassword);
        loginButton.click();

        String currentLoginName = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] p strong")).getText();
        Assertions.assertEquals(correctName, currentLoginName, "Log in failed");
    }

    @Test
    public void correctNameCorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(correctName);
        passwordField.sendKeys(correctPassword);
        loginButton.click();

        String currentLoginName = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] p strong")).getText();
        Assertions.assertEquals(correctName, currentLoginName, "Log in failed");
    }

    @Test
    public void incorrectEmailIncorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(incorrectValue + emailDomain);
        passwordField.sendKeys(incorrectValue);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedIncorrectEmailAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void correctNameIncorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(correctName);
        passwordField.sendKeys(incorrectValue);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedIncorrectPasswordForNameAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void correctEmailIncorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(correctName + emailDomain);
        passwordField.sendKeys(incorrectValue);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedIncorrectPasswordForEmailAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void incorrectEmailCorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(incorrectValue + emailDomain);
        passwordField.sendKeys(correctPassword);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedIncorrectEmailAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void incorrectNameCorrectPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(incorrectValue);
        passwordField.sendKeys(correctPassword);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedIncorrectNameAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedIncorrectNameAlert);
    }

    @Test
    public void emptyNameOrEmail() {
        WebElement passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        passwordField.sendKeys(correctPassword);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedEmptyNameAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }

    @Test
    public void emptyPassword() {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(incorrectValue);
        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedEmptyPasswordAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedEmptyPasswordAlert);
    }

    @Test
    public void emptyNameAndPassword() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginButton.click();

        String currentAlert = driver.findElement(By.cssSelector("ul[role='alert']")).getText();
        Assertions.assertEquals(expectedEmptyNameAlert, currentAlert, "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }
}

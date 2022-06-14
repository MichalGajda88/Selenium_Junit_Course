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
            correctEmail = "biosysit@gmail.com",
            incorrectEmail = "dasszxfe@gmail.com",
            correctPassword = "biosys1234",
            incorrectValue = "abcd1234";

    String expectedIncorrectEmailAlert = "Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.",
            expectedIncorrectNameAlert = "Błąd: Brak " + incorrectValue + " wśród zarejestrowanych w witrynie użytkowników. " +
                    "Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.",
            expectedEmptyNameAlert = "Błąd: Nazwa użytkownika jest wymagana.",
            expectedIncorrectPasswordForEmailAlert = "Błąd: Dla adresu e-mail " + correctEmail +
                    " podano nieprawidłowe hasło. Nie pamiętasz hasła?",
            expectedIncorrectPasswordForNameAlert = "Błąd: Wprowadzone hasło dla użytkownika biosysit jest niepoprawne. Nie pamiętasz hasła?",
            expectedEmptyPasswordAlert = "Błąd: Hasło jest puste.";

    private void userRegister(String userNameOrEmail, String userPassword) {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(userNameOrEmail);
        passwordField.sendKeys(userPassword);
        loginButton.click();
    }
    private String getUserName(){
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] p strong")).getText();
    }

    private String getAlertText(){
        return driver.findElement(By.cssSelector("ul[role='alert']")).getText();
    }

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
        userRegister(correctEmail, correctPassword);
        Assertions.assertEquals(correctName, getUserName(), "Log in failed");
    }

    @Test
    public void correctNameCorrectPassword() {
        userRegister(correctName, correctPassword);
        Assertions.assertEquals(correctName, getUserName(), "Log in failed");
    }

    @Test
    public void incorrectEmailIncorrectPassword() {
        userRegister(incorrectEmail, incorrectValue);
        Assertions.assertEquals(expectedIncorrectEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void correctNameIncorrectPassword() {
        userRegister(correctName, incorrectValue);
        Assertions.assertEquals(expectedIncorrectPasswordForNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void correctEmailIncorrectPassword() {
        userRegister(correctEmail, incorrectValue);
        Assertions.assertEquals(expectedIncorrectPasswordForEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void incorrectEmailCorrectPassword() {
        userRegister(incorrectEmail, correctPassword);
        Assertions.assertEquals(expectedIncorrectEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void incorrectNameCorrectPassword() {
        userRegister(incorrectValue, correctPassword);
        Assertions.assertEquals(expectedIncorrectNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectNameAlert);
    }

    @Test
    public void emptyNameOrEmail() {
        userRegister("", correctPassword);
        Assertions.assertEquals(expectedEmptyNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }

    @Test
    public void emptyPassword() {
        userRegister(correctName, "");
        Assertions.assertEquals(expectedEmptyPasswordAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyPasswordAlert);
    }

    @Test
    public void emptyNameAndPassword() {
        userRegister("", "");
        Assertions.assertEquals(expectedEmptyNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }
}

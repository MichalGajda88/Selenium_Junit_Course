package Interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
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

    private void userLogIn(String userNameOrEmail, String userPassword) {
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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @Test
    public void correctEmailCorrectPassword() {
        userLogIn(correctEmail, correctPassword);
        Assertions.assertEquals(correctName, getUserName(), "Log in failed");
    }

    @Test
    public void correctNameCorrectPassword() {
        userLogIn(correctName, correctPassword);
        Assertions.assertEquals(correctName, getUserName(), "Log in failed");
    }

    @Test
    public void incorrectEmailIncorrectPassword() {
        userLogIn(incorrectEmail, incorrectValue);
        Assertions.assertEquals(expectedIncorrectEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void correctNameIncorrectPassword() {
        userLogIn(correctName, incorrectValue);
        Assertions.assertEquals(expectedIncorrectPasswordForNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void correctEmailIncorrectPassword() {
        userLogIn(correctEmail, incorrectValue);
        Assertions.assertEquals(expectedIncorrectPasswordForEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectPasswordForEmailAlert);
    }

    @Test
    public void incorrectEmailCorrectPassword() {
        userLogIn(incorrectEmail, correctPassword);
        Assertions.assertEquals(expectedIncorrectEmailAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectEmailAlert);
    }

    @Test
    public void incorrectNameCorrectPassword() {
        userLogIn(incorrectValue, correctPassword);
        Assertions.assertEquals(expectedIncorrectNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedIncorrectNameAlert);
    }

    @Test
    public void emptyNameOrEmail() {
        userLogIn("", correctPassword);
        Assertions.assertEquals(expectedEmptyNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }

    @Test
    public void emptyPassword() {
        userLogIn(correctName, "");
        Assertions.assertEquals(expectedEmptyPasswordAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyPasswordAlert);
    }

    @Test
    public void emptyNameAndPassword() {
        userLogIn("", "");
        Assertions.assertEquals(expectedEmptyNameAlert, getAlertText(), "Alert different than expected. Expected: " +
                expectedEmptyNameAlert);
    }
}

package Interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ParametrizedLogInTest {
    WebDriver driver;
    WebDriverWait wait;
    String userExpectedName = "biosysit",
            password = "biosys1234";

    private void userLogIn(String userNameOrEmail, String userPassword) {
        WebElement loginField = driver.findElement(By.cssSelector("input[id='username']")),
                passwordField = driver.findElement(By.cssSelector("input[id='password']")),
                loginButton = driver.findElement(By.cssSelector("button[class*='woocommerce-form-login__submit']"));

        loginField.sendKeys(userNameOrEmail);
        passwordField.sendKeys(userPassword);
        loginButton.click();
    }

    private String getUserName() {
        return driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content'] p strong")).getText();
    }

    private String getAlertText() {
        return driver.findElement(By.cssSelector("ul[role='alert']")).getText();
    }

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @DisplayName("Successful log in")
    @ParameterizedTest(name = "User: \"{0}\"")
    @CsvSource({"biosysit", "biosysit@gmail.com"})
    void positiveCases(String userName) {
        userLogIn(userName, password);
        Assertions.assertTrue(getUserName().contains(userExpectedName), "Incorrect user name: " + userName +
                " Expected user name: " + getUserName());
    }

    @DisplayName("Unsuccessful log in")
    @ParameterizedTest(name = "User: {0}  password: {1}")
    @CsvSource({"wrongEmail@sasas.pl, wrongPassoword, Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.",
            "wrongName, wrongPassword, 'Błąd: Brak wrongName wśród zarejestrowanych w witrynie użytkowników. Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.'",
            "'', wrongPassword, 'Błąd: Nazwa użytkownika jest wymagana.'",
            "biosysit@gmail.com, wrongPassword, 'Błąd: Dla adresu e-mail biosysit@gmail.com podano nieprawidłowe hasło. Nie pamiętasz hasła?'",
            "biosysit, wrongPassword, 'Błąd: Wprowadzone hasło dla użytkownika biosysit jest niepoprawne. Nie pamiętasz hasła?'",
            "biosysit, '', 'Błąd: Hasło jest puste.'",
            "biosysit@gmail.com, '', 'Błąd: Hasło jest puste.'",
            "'', '', 'Błąd: Nazwa użytkownika jest wymagana.'"})
    void negativeCases(String userName, String password, String expectedAlertText) {
        userLogIn(userName, password);
        Assertions.assertEquals(expectedAlertText, getAlertText(), "Alert message: " + getAlertText() + " is different than expected one: " +
                expectedAlertText);
    }
}


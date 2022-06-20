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

import java.util.concurrent.TimeUnit;

public class ParametrizedTest {
    WebDriver driver;
    String

            password = "biosys1234";

    String expectedIncorrectEmailAlert = "Nieznany adres e-mail. Proszę sprawdzić ponownie lub wypróbować swoją nazwę użytkownika.",
            expectedIncorrectNameAlert = "Błąd: Brak " + " wśród zarejestrowanych w witrynie użytkowników. " +
                    "Jeśli nie masz pewności co do nazwy użytkownika, użyj adresu e-mail.",
            expectedEmptyNameAlert = "Błąd: Nazwa użytkownika jest wymagana.",
            expectedIncorrectPasswordForEmailAlert = "Błąd: Dla adresu e-mail "  +
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

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void driverQuit() {
        driver.quit();
    }

    @DisplayName("Possitive case")
    @ParameterizedTest(name = "User: \"{0}\"")
    @CsvSource({"biosysit", "biosysit@gmail.com"})

    void possitiveCase (String userName){
        userLogIn(userName, password);
        Assertions.assertTrue(getUserName().contains(userName), "Incorrect user name: " + userName + " Expected user name: " + getUserName());
    }
}


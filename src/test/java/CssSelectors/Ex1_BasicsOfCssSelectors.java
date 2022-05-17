package CssSelectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex1_BasicsOfCssSelectors {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://fakestore.testelka.pl/moje-konto/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findByCssSelectors() {
        // pole do wpisania nazwy użytkownika (logowanie)
        driver.findElement(By.cssSelector("#username"));
        // pole do wpisania hasła (logowanie)
        driver.findElement(By.cssSelector("#reg_email"));
        // checkbox „Zapamiętaj mnie”
        driver.findElement(By.cssSelector("#rememberme"));
        // przycisk „Zaloguj”
        driver.findElement(By.cssSelector(".woocommerce-form-login__submit"));
        // link do odzyskiwania hasła
        driver.findElement(By.cssSelector("a[href='https://fakestore.testelka.pl/moje-konto/zapomniane-haslo/']"));
        // pole do wpisania adresu email (rejestracja)
        driver.findElement(By.cssSelector("#reg_email"));
        //pole do wpisania hasła (rejestracja),
        driver.findElement(By.cssSelector("#reg_password"));
        // przycisk „Zarejestruj się”
        driver.findElement(By.cssSelector(".woocommerce-form-register__submit"));
        // link do kategorii „Windsurfing”
        driver.findElement(By.cssSelector("a[href='https://fakestore.testelka.pl/product-category/windsurfing/']"));
    }

}

package CssSelectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex2_Relations {

    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    //Zbuduj selektor do przycisku „Dodaj do koszyka”, który znajduje się na stronie głównej fakestore.testelka.pl pod
    //wycieczką na Fuertaventurę w sekcji „Nowości”
    @Test
    public void findAddToCartButton() {
        driver.findElement(By.cssSelector("section.storefront-recent-products a[data-product_id='393']"));
    }

    //Zbuduj selektor do listy rozwijanej (elementu o tagu select) na stronie kategorii Windsurfing, który znajduje się
    //nad produktami (na stronie są dwie takie listy rozwijane, chodzi o tą pierwszą)
    @Test
    public void findListOfSorting() {
        driver.findElement(By.cssSelector("header.woocommerce-products-header+div form select"));
    }

    //Dodaj jakiś produkt do koszyka i przejdź na stronę koszyka. Zbuduj selektor, który zaznaczy cenę (wraz ze skrótem
    //waluty czyli zł), która znajduje się na poziomie menu na stronie (po prawej od listy życzeń).
    @Test
    public void findPrice() {
        driver.get("https://fakestore.testelka.pl/koszyk/");
        driver.findElement(By.cssSelector("a.cart-contents>span.amount"));
    }
}

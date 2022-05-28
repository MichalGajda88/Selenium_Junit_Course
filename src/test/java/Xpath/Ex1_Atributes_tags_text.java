package Xpath;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex1_Atributes_tags_text {
    WebDriver driver;

    @BeforeEach
    public void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void addProductsToCart() {
        //dodać waity
        driver.get("https://fakestore.testelka.pl/product-category/wspinaczka/");
        driver.findElement(By.xpath(".//a[@data-product_id='4114']")).click();
        driver.findElement(By.xpath(".//a[@data-product_id='42']")).click();
        driver.get("https://fakestore.testelka.pl/koszyk/");
    }

    @AfterEach
    public void closeAndQuit() {
        driver.close();
        driver.quit();
    }

    @Test
    public void findAddedProducts() {
        // Zaznacz jeden z produktów (tylko jeden, nie oba). Najpierw się umów sam lub sama ze sobą który,
        // a potem spróbuj go zaznaczyć. Chodzi o element przechowujący nazwę produktu. Na filmie tłumaczę dokładnie o co mi chodzi.
        driver.findElement(By.xpath(".//td[@class='product-name' and contains(text(),'Wspinaczka Island Peak')]"));
        // Zaznacz checkbox „Stworzyć konto?” (ale tylko checkbox, bez tekstu).
        driver.findElement(By.xpath(".//input[@id='createaccount']"));
        // Zaznacz jednym XPathem dwa pola adresowe (nazwa ulicy i ciąg dalszy adresu).
        driver.findElement(By.xpath(".//input[starts-with(@id, 'billing_address')]"));
    }


}

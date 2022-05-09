package FirstSteps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex1_WikiTest {
    WebDriver driver;

    @BeforeEach
    public void driverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.get("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
    }

    @AfterEach
    public void driverQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void isTitlePolish() {
        String polishWikiTitle = driver.getTitle();
        Assertions.assertEquals(driver.getTitle(), polishWikiTitle, "Page title is not: " + polishWikiTitle);
    }

    @Test
    public void isUrlPolish(){
        String polishWikiUrl = driver.getCurrentUrl();
        Assertions.assertEquals(driver.getCurrentUrl(), polishWikiUrl, "Page URL is not: " + polishWikiUrl);
    }

    @Test
    public void isLanguagePolish() {
        String polishLanuage = "lang=\"pl\"";
        Assertions.assertTrue(driver.getPageSource().contains(polishLanuage), "Page source does not contain language information");
    }

    @Test
    public void languageChangeSpanish(){
        driver.findElement(By.cssSelector("a[title='hiszpański']")).click();
        String wikiTitle = driver.getTitle();
        Assertions.assertEquals("Wikipedia, la enciclopedia libre", wikiTitle, "Page title is not: " + wikiTitle);

        String wikiUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://es.wikipedia.org/wiki/Wikipedia:Portada", wikiUrl, "Page URL is not: " + wikiUrl);

        String lanuage = "lang=\"es\"";
        Assertions.assertTrue(driver.getPageSource().contains(lanuage), "Page language is not Spanish");

    }

}

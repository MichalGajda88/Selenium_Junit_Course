package FirstSteps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
     WebDriver driver;

        @BeforeEach
        public void driverSetup(){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1920,1080));
        }

        @AfterEach
        public void driverQuit(){
            driver.close();
            driver.quit();
        }


        @Test
        public void navigateTo(){
            String wikipedia = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
            String nasa = "https://www.nasa.gov/";
            String wikititle = "Wikipedia, wolna encyklopedia";
            String nasatitle = "NASA";

            driver.navigate().to(wikipedia);
            driver.navigate().to(nasa);
            driver.navigate().back();
            Assertions.assertEquals(wikititle, driver.getTitle(), "Wrong page title");

            driver.navigate().forward();
            Assertions.assertEquals(nasatitle, driver.getTitle(), "Wrong page title");
        }
        @Test
        public void checkRedirection(){
            String googleUrl = "https://www.google.pl/";
            driver.navigate().to("https://google.pl");

            Assertions.assertEquals(googleUrl, driver.getCurrentUrl(), "URL incorrect");
        }

        @Test
        public void getPageSource(){
            String sympatiaImage = "https://ocdn.eu/onetmobilemainpage/mainpage_desktop/logout/logoutsympatia.png";
            driver.get("https://www.onet.pl");
            Assertions.assertTrue(driver.getPageSource().contains(sympatiaImage), "Page not contains image");
        }

    }


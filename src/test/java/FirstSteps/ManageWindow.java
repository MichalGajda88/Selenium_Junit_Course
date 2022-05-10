package FirstSteps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageWindow {
    WebDriver driver;

    @BeforeEach
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", "src//main//resources//chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().setPosition(new Point(30,30));
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.get("https://www.onet.pl");
    }

    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }

    @Test
    public void checkWindowSettings(){
        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(30,30), position, "Window position is incorrect");

        Dimension dimension = driver.manage().window().getSize();
        Assertions.assertEquals(new Dimension(1920,1080), dimension, "Window dimensions are incorrect");
    }

    @Test
    public void setFullScreen() throws InterruptedException {
        Thread.sleep(2000);
        driver.manage().window().fullscreen();
    }

    @Test
    public void setMaximize() throws InterruptedException {
        Thread.sleep(2000);
        driver.manage().window().maximize();
    }
}

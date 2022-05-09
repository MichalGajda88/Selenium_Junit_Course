package FirstSteps;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ex3_WindowSettings {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        // point 1
        driver.manage().window().setSize(new Dimension(854,480));
        //point 2
        driver.manage().window().setPosition(new Point(445,30));
        driver.get("https://www.onet.pl");
    }

    @AfterEach
    public void closeAndQuit(){
        driver.close();
        driver.quit();
    }

    //point 3
    @Test
    public void getWindowSize(){
       Dimension dimension = driver.manage().window().getSize();
       Assertions.assertEquals(new Dimension(854,480), dimension, "Window size is incorrect" );
    }

    //point 4
    @Test
    public void getWindowPosition(){
        Point position = driver.manage().window().getPosition();
        Assertions.assertEquals(new Point(445,30), position, "Window position is incorrect");
    }

    //point 5
    @Test
    public void setMaximize() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    //point 6
    @Test
    public void setFullscreen() throws InterruptedException {
        driver.manage().window().fullscreen();
        Thread.sleep(2000);
    }

}

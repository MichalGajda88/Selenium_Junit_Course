package Interactions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class SendFile {
    WebDriver driver;

    @BeforeEach
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        driver.manage().window().maximize();
        driver.get("https://liteshare.co/");
    }

    @AfterEach
    public void driverQuit(){
        driver.quit();
    }

    @Test
    public void uploadFileTest(){
        WebElement inputFile = driver.findElement(By.cssSelector("input[type='file']"));
        String expectedName = "basiclinuxcommands.jpg";
        String filePath = "I:\\Java-projects\\Kurs-Testelka\\test\\src\\main\\resources\\" + expectedName;

        inputFile.sendKeys(filePath);
        String actualName = driver.findElement(By.xpath(".//tbody[@id='fileHolder']/tr/td[1]")).getText();
        Assertions.assertEquals(expectedName, actualName, "Name of the file is different than expected");
    }
}
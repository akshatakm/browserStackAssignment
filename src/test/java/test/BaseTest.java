package test;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

public class BaseTest {
    WebDriver driver;
    Properties prop = new Properties();

    @BeforeClass
    public WebDriver init_driver() {
        System.out.println("driver initialtion...");
        init_properties();
        String browser = prop.getProperty("browser");
        if (browser.equals("chrome")) {
            System.out.println("chrome browser");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            System.out.println("Please provide a proper browser value..");
        }

        //driver.manage().window().fullscreen();
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("url"));

        return driver;
    }

    private void init_properties() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("/Users/akshathak/Documents/browserStack/src/test/resources/driver.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @AfterClass
    public void TeardownTest()
    {
        driver.quit();
    }
}


package pages;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
    WebDriver driver;
    WebDriverWait wait;
    public boolean isElementVisible(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean isElementClickable(WebElement element){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        }catch(Exception e){
            return false;
        }
    }
}


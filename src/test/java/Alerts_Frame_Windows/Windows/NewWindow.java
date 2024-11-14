package Alerts_Frame_Windows.Windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertNotEquals;

public class NewWindow {
    WebDriver driver;
    String url = "https://demoqa.com/browser-windows";

    @BeforeTest
    public void open_page(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close_page(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    public void window(){
        String originalHandle = driver.getWindowHandle();

        String originalURL = driver.getCurrentUrl();

        String curentURL = "";

        WebElement button = driver.findElement(By.id("windowButton"));

        button.click();

        Set<String> allWindowHandles = driver.getWindowHandles();

        for(String handle : allWindowHandles){
            if(handle != originalHandle){
                driver.switchTo().window(handle);
                System.out.println("The other window handle is: " + handle + " the url for the handle is: " + driver.getCurrentUrl());
                curentURL = driver.getCurrentUrl();
            }
        }

        assertNotEquals(curentURL,originalURL);
    }
}

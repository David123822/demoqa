package Alerts_Frame_Windows.Windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import static org.testng.AssertJUnit.assertTrue;

public class NewMessageWindow {
    WebDriver driver;
    String url = "https://demoqa.com/browser-windows";

    @BeforeTest
    public void open_page(){

        driver = new EdgeDriver();
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
    public void messadeWindow() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String bodyMessage = "";

        String expectedMessage = "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.";

        String originalHandle = driver.getWindowHandle();

        WebElement button = driver.findElement(By.id("messageWindowButton"));

        Random random = new Random();
        int delay = random.nextInt(2000) + 1000;  // Between 1 to 3 seconds
        Thread.sleep(delay);

        button.click();

        Set<String> allHandles = driver.getWindowHandles();

        for(String handle : allHandles){
            if(handle != originalHandle){
                System.out.println("The other window handle is: " + handle + " the url for the handle is: " + driver.getCurrentUrl());

                Thread.sleep(delay);

                driver.switchTo().window(handle);

                Thread.sleep(delay);

                WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("body")));

                Thread.sleep(delay);

                bodyMessage = body.getText();
            }
        }

        assertTrue(bodyMessage.contains(expectedMessage));
    }
}

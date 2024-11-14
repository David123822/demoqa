package Alerts_Frame_Windows.Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class DelayAlert {
    WebDriver driver;
    String url = "https://demoqa.com/alerts";
    WebDriverWait wait;


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
    public void delatAlert(){
        String expected = "This alert appeared after 5 seconds";

        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement button = driver.findElement(By.id("timerAlertButton"));
        button.click();

        Alert timerAlert = wait.until(ExpectedConditions.alertIsPresent());

        String alertText = timerAlert.getText();

        timerAlert.accept();

        assertTrue(alertText.contains(expected));
    }
}

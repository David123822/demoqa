package Alerts_Frame_Windows.Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class BasicAlert {
    WebDriver driver;
    String url = "https://demoqa.com/alerts";

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
    public void basicAlert(){
        String expected = "You clicked a button";

        WebElement button = driver.findElement(By.id("alertButton"));
        button.click();

        Alert simpleAlert = driver.switchTo().alert();

        String alertText = simpleAlert.getText();

        simpleAlert.accept();

        assertTrue(alertText.contains(expected));
    }
}

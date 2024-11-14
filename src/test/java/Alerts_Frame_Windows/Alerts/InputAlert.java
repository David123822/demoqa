package Alerts_Frame_Windows.Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class InputAlert {
    WebDriver driver;
    String url = "https://demoqa.com/alerts";


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
    public void inputAlert(){

        String decision = "fail";

        WebElement button = driver.findElement(By.id("promtButton"));
        button.click();

        Alert inputAlert = driver.switchTo().alert();

        if(decision.equals("pass")){
            inputAlert.sendKeys("Testing");

            inputAlert.accept();

            WebElement result = driver.findElement(By.id("promptResult"));

            assertTrue(result.getText().contains("Testing"));

            System.out.println("The result message: " + result.getText());

        } else if (decision.equals("fail")) {
            // Dismiss the alert (click "Cancel")
            inputAlert.dismiss();

            // Verify that the alert is no longer present
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean alertStillPresent = wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));

            assertTrue("Alert should be dismissed and no longer present", alertStillPresent);

            // Since we dismissed the alert, there should be no result text change expected
            System.out.println("Alert was dismissed successfully. No changes expected in result.");
        }
    }
}

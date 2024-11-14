package Alerts_Frame_Windows.Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class DecisionAlert {
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
    public void decisionAlert(){
        String decision = "fail";

        WebElement button = driver.findElement(By.id("confirmButton"));
        button.click();

        Alert decisionAlert = driver.switchTo().alert();

        if(decision.equals("pass")){
            decisionAlert.accept();

            WebElement message = driver.findElement(By.id("confirmResult"));

            assertTrue(message.getText().contains("Ok"));

        } else if (decision.equals("fail")) {
            decisionAlert.dismiss();

            WebElement message = driver.findElement(By.id("confirmResult"));

            assertTrue(message.getText().contains("Cancel"));
        }
    }
}

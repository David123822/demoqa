package Elements.DynamicProperties;

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

public class VisibleTest {
    WebDriver driver;
    WebDriverWait wait;
    String url = "https://demoqa.com/dynamic-properties";

    @BeforeTest
    public void open_page(){
        driver =  new ChromeDriver();
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
    public void test(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("visibleAfter")));

        assertTrue("Button is not visible", button.isDisplayed());
    }
}

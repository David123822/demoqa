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

import static org.testng.AssertJUnit.assertFalse;

public class ColorChange {
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
    public void color_change() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));

        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorChange")));

        String initialColor = button.getCssValue("color");

        Thread.sleep(6000);

        String lastColor = button.getCssValue("color");

        assertFalse("The color did not change", lastColor.contains(initialColor));
    }
}

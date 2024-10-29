import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Butons {

    WebDriver driver;
    String url = "https://demoqa.com/buttons";

    @BeforeTest
    public void open_the_page(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void close_the_page(){
        if (driver != null){
            driver.close();
        }
    }

    @Test
    public void doubleClick(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#doubleClickBtn")));

        Actions action = new Actions(driver);
        action.doubleClick(button).perform();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickMessage")));

        assertTrue("The button has not been clicked twice",result.isDisplayed());
    }

    @Test
    public void rightClick(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions action = new Actions(driver);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#rightClickBtn")));

        // making a right click in Selenium
        action.contextClick(button).perform();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rightClickMessage")));

        assertTrue("The button has not been clicked twice",result.isDisplayed());
    }

    @Test
    public void click(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // the ID of the button was changing each time so it is better to choose a different method to try and find it, the text on the button for example here
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click Me']")));

        button.click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicClickMessage")));

        assertTrue("The button has not been clicked twice",result.isDisplayed());
    }
}

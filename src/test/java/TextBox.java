import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class TextBox {

    String url = "https://demoqa.com/text-box";
    WebDriver driver;

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
    @Parameters({"name", "uemail", "taddress", "paddress"})
    public void text_box(String name, String uemail, String taddress, String paddress){

        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));

        // full name field
        WebElement fullName = wait.until(ExpectedConditions.elementToBeClickable(By.id("userName")));//driver.findElement(By.id("userName"));
        fullName.sendKeys(name);

        // email
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys(uemail);

        // address
        WebElement address = driver.findElement(By.id("currentAddress"));
        address.sendKeys(taddress);

        // permanent address
        WebElement pAddress = driver.findElement(By.id("permanentAddress"));
        pAddress.sendKeys(paddress);

        // submit button
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));//driver.findElement(By.id("submit"));
        //Actions action = new Actions(driver);
        //action.moveToElement(button);
        button.sendKeys(Keys.ARROW_DOWN);
        button.click();

        // last field
        WebElement display = driver.findElement(By.cssSelector(".border.col-md-12.col-sm-12"));

        assertTrue("An error somewhere ",display.isDisplayed());
    }
}

package Links;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Optional;

import java.util.Optional;

import static org.testng.AssertJUnit.assertTrue;

public class No_Content_APIcall {

    WebDriver driver;
    DevTools devtools;
    String url = "https://demoqa.com/links";
    JavascriptExecutor jExecute;

    @BeforeTest
    public void open_page(){
        driver = new ChromeDriver();

        jExecute = (JavascriptExecutor) driver;

        // Setup Devtools
        devtools = ((ChromeDriver) driver).getDevTools();
        devtools.createSession();

        // Enable the traffic tracking
        devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));


            // Add a listener to capture network responses from the server
        devtools.addListener(Network.responseReceived(), responseReceived -> {
            String responseURL = responseReceived.getResponse().getUrl();
            int statusCode = responseReceived.getResponse().getStatus();
            String statusText = responseReceived.getResponse().getStatusText();

            // Now filter specifically for 204 responses
            if(responseURL.contains("no-content") && statusCode == 204){
                System.out.println("API call successful for 'no content' link.");
                System.out.println("Status Code: " + statusCode);
                System.out.println("Status Text: " + statusText);
            }
        });

        driver.get(url); // Launch the URL
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close_the_page() {
        // Close the browser and end the session after the test
        if (driver != null) {
            driver.quit(); // Quit will close all tabs and terminate the session properly
        }
    }

    @Test
    public void test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.id("no-content")));

        jExecute.executeScript("arguments[0].scrollIntoView(true)", link);
        link.click();

        WebElement statusMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("linkResponse")));

        String message = statusMessage.getText();

        assertTrue("Status code 204 not found!",message.contains("204"));

        System.out.println("Page message: " + message);

    }
}

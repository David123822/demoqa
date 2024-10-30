package Elements.Links;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v124.network.Network;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Optional;

import static org.testng.AssertJUnit.assertTrue;

public class Not_Found_APIcall {

    WebDriver driver;
    WebDriverWait wait;
    DevTools devtools;
    JavascriptExecutor jExecutor;
    String url = "https://demoqa.com/links";
    String statusText;

    @BeforeTest
    public void open_page(){
        driver = new ChromeDriver();

        jExecutor = (JavascriptExecutor) driver;

        devtools = ((ChromeDriver) driver).getDevTools();

        devtools.createSession();

        devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devtools.addListener(Network.responseReceived(), responseReceived -> {
            String resURL = responseReceived.getResponse().getUrl();
            int statusCode = responseReceived.getResponse().getStatus();
            statusText = responseReceived.getResponse().getStatusText();

            if(resURL.contains("invalid-url") && statusCode == 404){
                System.out.println("API call for link: " + resURL);
                System.out.println("Status code: " + statusCode);
                System.out.println("Status text: " + statusText);
            }
        });

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        WebElement link = driver.findElement(By.id("invalid-url"));

        jExecutor.executeScript("arguments[0].scrollIntoView(true)", link);

        link.click();

        WebElement resoponseMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("linkResponse")));
        String resMessage = resoponseMessage.getText();

        assertTrue("Some error somewhere :)", resMessage.contains(statusText));

        System.out.println("Page message: " + resMessage);
    }
}

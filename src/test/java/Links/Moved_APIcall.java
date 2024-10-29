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

import static org.testng.AssertJUnit.assertTrue;

public class Moved_APIcall {
    WebDriver driver;
    JavascriptExecutor jExecute;
    DevTools devTools;
    String url = "https://demoqa.com/links";

    @BeforeTest
    public void open_page(){
        driver = new ChromeDriver();

        jExecute = (JavascriptExecutor) driver;

        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(), Optional.empty()));

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            String responseURL = responseReceived.getResponse().getUrl();
            int statusCode = responseReceived.getResponse().getStatus();
            String  statusText = responseReceived.getResponse().getStatusText();

            if(responseURL.contains("moved") && statusCode == 301){
                System.out.println("API call for the link: " + responseURL);
                System.out.println("Status Code: " + statusCode);
                System.out.println("Status Text: " + statusText);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        WebElement link = driver.findElement(By.id("moved"));
        jExecute.executeScript("arguments[0].scrollIntoView(true)", link);

        link.click();

        WebElement statusMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("linkResponse")));

        String pageMessage = statusMessage.getText();

        assertTrue("There is an issue", pageMessage.contains("301"));

        System.out.println("Page message: " + pageMessage);
    }
}

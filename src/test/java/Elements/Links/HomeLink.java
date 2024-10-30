package Elements.Links;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class HomeLink {
    WebDriver driver;
    String url = "https://demoqa.com/links";

    @BeforeTest
    public void open_the_page(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void close_the_page(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void homeTest(){

        WebElement homeLink = driver.findElement(By.id("simpleLink"));
        homeLink.click();

        /*
When a link opens a new tab or window, Selenium doesn't automatically switch to the new tab. You need to explicitly switch the driver's context to the newly opened tab/window, perform the actions/assertions, and then optionally switch back to the original tab.

Here’s how you can modify your test to handle this scenario:

Steps:
Click the link.
Get all the window handles after the new tab is opened.
Switch to the new window/tab.
Perform your actions or assertions on the new tab.
Switch back to the original tab (optional if you want to return to the original tab after the test).*/

        // this is soo fuckinn cool

        // Get the original window handle
        String originalWindow = driver.getWindowHandle();

        // Wait until a new window/tab is opened (There should be two windows now)
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);  // Switch to the new window
                break;
            }
        }

        // Now you are in the new tab/window
        String currentURL = driver.getCurrentUrl();
        assertTrue("The new tab was not opened",!currentURL.contains(url));
    }
}

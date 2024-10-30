package Elements.Broken_Links_Image;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class BrokenLinks {
    WebDriver driver;
    String url = "https://demoqa.com/broken";

    @BeforeTest
    public void open_page() {
        // Initialize the WebDriver (ChromeDriver) and open the target URL
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close_page() {
        // Safely close the browser after tests are done
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Parameters({"linkHref"})
    public void broken_link(String linkHref) throws IOException {
        List<WebElement> links = driver.findElements(By.tagName("a"));

        boolean isFound = false;

        for(WebElement link : links){
            String href = link.getAttribute("href");

            if(href != null && href.equals(linkHref)){
                isFound = true;
                boolean response = checkLink(linkHref);

                if(response){
                    System.out.println("Link is valid: " + href);
                    assertTrue("Link is broken", response);
                }
                else{
                    System.out.println("Link is broken: " + href);
                    assertFalse("Link is valid", response);
                }
            }
        }
    }

    public static boolean checkLink(String urlLink) throws IOException {
        // turns a string into a url
        URL url = new URL(urlLink);

        // connect to the URl
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        httpConnection.setRequestMethod("GET");

        httpConnection.setConnectTimeout(3000);

        // connect to the URL
        httpConnection.connect();

        //get the status codes
        int statusCode = httpConnection.getResponseCode();

        if(statusCode >= 400){
            return false;
        }
        else{
            return true;
        }
    }

}

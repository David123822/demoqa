package Links;

// Import necessary Selenium, Chrome DevTools, and TestNG libraries
import org.openqa.selenium.By; // Provides methods for finding elements by locators
import org.openqa.selenium.WebDriver; // WebDriver is the interface for controlling the browser
import org.openqa.selenium.WebElement; // Represents a DOM element on the webpage
import org.openqa.selenium.chrome.ChromeDriver; // ChromeDriver for controlling Chrome browser
import org.openqa.selenium.chrome.ChromeOptions; // Options to customize ChromeDriver
import org.openqa.selenium.devtools.DevTools; // To interact with Chrome DevTools API
import org.openqa.selenium.devtools.v85.network.Network; // Network tracking with DevTools protocol
import org.openqa.selenium.support.ui.ExpectedConditions; // Conditions for explicit waits
import org.openqa.selenium.support.ui.WebDriverWait; // Explicit wait for elements
import org.testng.annotations.AfterTest; // TestNG annotation for actions after the test
import org.testng.annotations.BeforeTest; // TestNG annotation for actions before the test
import org.testng.annotations.Test; // TestNG annotation to mark a method as a test

import java.time.Duration; // Time duration for waits
import java.util.Optional; // For handling optional parameters

public class APICalls {
    // Declare WebDriver and DevTools
    WebDriver driver;
    DevTools devTools;

    // URL of the webpage we will interact with
    String url = "https://demoqa.com/links";

    @BeforeTest
    public void open_the_page() {
        // Setup ChromeDriver with default options
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options); // Initialize ChromeDriver to control Chrome

        // Setup DevTools for network traffic monitoring
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession(); // Start a DevTools session to interact with the browser

        // Enable network traffic tracking via DevTools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Add a listener to capture outgoing network requests
        devTools.addListener(Network.requestWillBeSent(), request -> {
            String requestUrl = request.getRequest().getUrl(); // Get the URL of the outgoing request

            // Check if the outgoing request is related to the 'created' link
            if (requestUrl.contains("created")) {
                System.out.println("Detected API call for 'created' link: " + requestUrl);
            }
        });

        // Add a listener to capture network responses from the server
        devTools.addListener(Network.responseReceived(), response -> {
            String responseUrl = response.getResponse().getUrl(); // Get the URL of the response
            int statusCode = response.getResponse().getStatus(); // Get the HTTP status code of the response
            String statusText = response.getResponse().getStatusText(); // Get the HTTP status text

            // If the response is for the 'created' API call and has status 201 (Created), log the success
            if (responseUrl.contains("created") && statusCode == 201) {  // Check for 201 Created status
                System.out.println("API call successful for 'created' link.");
                System.out.println("Status Code: " + statusCode); // Log status code 201
                System.out.println("Status Text: " + statusText); // Log the status text ("Created")
            }
        });

        // Open the target webpage
        driver.get(url); // Launch the URL
        driver.manage().window().maximize(); // Maximize the browser window
    }

    @AfterTest
    public void close_the_page() {
        // Close the browser and end the session after the test
        if (driver != null) {
            driver.quit(); // Quit will close all tabs and terminate the session properly
        }
    }

    @Test
    public void apiCalls() {
        // Create a WebDriverWait instance to wait for elements to become available
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Locate the "created" link element using its ID and click on it
        WebElement created = driver.findElement(By.id("created"));
        created.click(); // Simulate a click on the 'created' link, which triggers an API call

        // Wait for the status message element to become clickable and then retrieve it
        WebElement statusMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("linkResponse")));
        String messageText = statusMessage.getText(); // Get the text displayed in the status message

        // Verify that the status message contains the expected status code '201' and text 'Created'
        assert messageText.contains("201") : "Status code 201 not found!"; // Assert that 201 is present
        assert messageText.contains("Created") : "Status text 'Created' not found!"; // Assert that 'Created' is present

        // Log the actual message displayed on the page after clicking the link
        System.out.println("Page message: " + messageText);
    }
}

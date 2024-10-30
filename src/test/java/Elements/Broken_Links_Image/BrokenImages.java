package Elements.Broken_Links_Image;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.List;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class BrokenImages {
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
    @Parameters({"imageSrc"})
    public void test_specific_image(String imageSrc) {
        // Find all <img> elements on the page
        List<WebElement> images = driver.findElements(By.tagName("img"));

        // Flag to check if the specified image was found
        boolean imageFound = false;

        // Loop through each image to find the one with the matching 'src' attribute
        for (WebElement image : images) {
            String src = image.getAttribute("src");

            // Check if the image's 'src' matches the provided parameter
            if (src != null && src.equals(imageSrc)) {
                imageFound = true;

                // Use JavaScript to check if the image's naturalWidth and naturalHeight are non-zero
                Boolean isImageLoaded = (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return arguments[0].naturalWidth > 0 && arguments[0].naturalHeight > 0", image);

                // Assert based on image load status and print informative messages
                if (isImageLoaded) {
                    System.out.println("Image loaded successfully: " + src);
                    assertTrue("Image is not loaded correctly", isImageLoaded);
                } else {
                    System.out.println("Image is broken: " + src);
                    assertFalse("Image loaded correctly", isImageLoaded);
                }
                break;  // Exit the loop once the specified image is found and tested
            }
        }

        // If the image was not found, print a message
        if (!imageFound) {
            System.out.println("Specified image with src '" + imageSrc + "' was not found on the page.");
        }
    }
}

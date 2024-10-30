package Elements.Upload_Download;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.AssertJUnit.assertTrue;

public class Download {

    WebDriver driver;
    String url = "https://demoqa.com/upload-download";

    @BeforeTest
    public void open_page() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    public void close_page() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void download_test() throws InterruptedException {
        // Find and click the download button
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        // Define the expected file name and download directory
        String fileName = "sampleFile.jpeg";
        Path downloadFolder = Paths.get(System.getProperty("user.home"), "Downloads");

        // Create a File object for the specified image in the Downloads folder

        File downloadedFile = new File(downloadFolder.toFile(), fileName);

        // Wait for the file to appear in the Downloads folder
        boolean fileExists = waitForFileToDownload(downloadedFile, 10);

        // Verify that the file was downloaded successfully
        assertTrue("The file was not downloaded", fileExists);
    }

    // Method to wait for a file to appear with retries
    public boolean waitForFileToDownload(File file, int maxRetries) throws InterruptedException {
        int retries = 0;
        while (retries < maxRetries) {
            if (file.exists()) {
                return true;
            }
            Thread.sleep(1000); // Wait 1 second before retrying
            retries++;
        }
        return false; // File not found within the time limit
    }
}

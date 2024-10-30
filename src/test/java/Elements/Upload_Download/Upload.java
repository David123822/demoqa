package Elements.Upload_Download;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class Upload {
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
    public void upload_test(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String fileName = "sampleFile.jpeg";

        Path downloadFolder = Paths.get(System.getProperty("user.home"), "Downloads");

        //Creating a file object for the file
        File sentFile = new File(downloadFolder.toFile(), fileName);
        //sentFile.getAbsolutePath()

        WebElement uploadButton = driver.findElement(By.id("uploadFile"));
        uploadButton.sendKeys(sentFile.getAbsolutePath());

        WebElement responseText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadedFilePath")));

        assertTrue("The file was not uploaded",responseText.isDisplayed());

    }
}

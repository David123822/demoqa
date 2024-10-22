package WebTables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class WebTables {

    WebDriver driver;
    String url = "https://demoqa.com/webtables";

    @BeforeTest
    public void open_the_page() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    //@AfterTest
    public void close_the_page() {
        if (driver != null) {
            driver.close();
        }
    }


}

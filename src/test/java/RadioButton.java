import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class RadioButton {
    WebDriver driver;
    String url = "https://demoqa.com/radio-button";

    @BeforeTest
    public void open_the_page() {
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void close_the_page() {
        if (driver != null) {
            driver.close();
        }
    }

    @Test
    @Parameters({"button"})
    public void radiobutton(@Optional ("impressive") String button) {

        JavascriptExecutor jExecutor = (JavascriptExecutor) driver;

        if(button.equals("yes")){
            // Locate the label associated with the yesRadio button
            WebElement yesLabel = driver.findElement(By.cssSelector("label[for='yesRadio']"));

            // Scroll into view if necessary
            jExecutor.executeScript("arguments[0].scrollIntoView(true);", yesLabel);

            // Click the label instead of the radio button input
            yesLabel.click();

            // Verify if the radio button is selected
            WebElement yesButton = driver.findElement(By.cssSelector("#yesRadio"));

            if(yesButton.isSelected()){

                String expectedText = "Yes";

                WebElement output = driver.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div:nth-child(3) > p"));

                assertTrue(output.getText().contains(expectedText), "The Yes button was not pressed");
            }
            else System.out.println("The button was not selected");

        } else if (button.equals("impressive")) {

            // find the label for the radio button
            WebElement impressiveLabel = driver.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div:nth-child(3) > div:nth-child(3) > label"));

            // Scroll into view if necessary
            jExecutor.executeScript("arguments[0].scrollIntoView(true);", impressiveLabel);

            // click the label
            impressiveLabel.click();

            // verify if the label was clicked
            WebElement impressiveButton = driver.findElement(By.cssSelector("#impressiveRadio"));

            if(impressiveButton.isSelected()){

                String expectedText = "Impressive";

                WebElement output = driver.findElement(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div:nth-child(3) > p"));

                assertTrue(output.getText().contains(expectedText), "The Impressive button was not pressed");
            }
            else System.out.println("The button was not selected");
        }

    }
}
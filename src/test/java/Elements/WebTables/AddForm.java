package Elements.WebTables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AddForm {

    WebDriver driver;
    String url = "https://demoqa.com/webtables";

    // Constructor that accepts WebDriver, but it's optional
    public AddForm(WebDriver driver) {
        this.driver = driver; // Assign the passed driver to the class-level variable
    }

    // No-argument constructor for standalone test run
    public AddForm() {
    }

    @BeforeTest
    public void open_the_page() {
        // Initialize WebDriver only if it's not already initialized (for external use)
        if (driver == null) {
            driver = new ChromeDriver();
            driver.get(url);
            driver.manage().window().maximize();
        }
    }

    @AfterTest
    public void close_the_page() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Parameters({"fName", "lName", "eml", "ag", "sal", "dep", "test"})
    public void addForm(@Optional("John") String fName, @Optional("Doe") String lName,
                        @Optional("john.doe@example.com") String eml, @Optional("30") String ag,
                        @Optional("5000") String sal, @Optional("Engineering") String dep,
                        @Optional("true") boolean test) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement add = driver.findElement(By.id("addNewRecordButton"));
        add.click();

        // Wait for the form modal to be visible
        WebElement formModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userForm")));

        // Fill out the form fields
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
        firstName.sendKeys(fName);

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName")));
        lastName.sendKeys(lName);

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));
        email.sendKeys(eml);

        WebElement age = wait.until(ExpectedConditions.elementToBeClickable(By.id("age")));
        age.sendKeys(ag);

        WebElement salary = wait.until(ExpectedConditions.elementToBeClickable(By.id("salary")));
        salary.sendKeys(sal);

        WebElement department = wait.until(ExpectedConditions.elementToBeClickable(By.id("department")));
        department.sendKeys(dep);

        // Find the submit button and click it
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submit.click();

        // Check if it's a valid submission (form should close)
        if (test) {
            try {
                // waits for the form to become invisible due to it being a dynamic item
                boolean isFormClosed = wait.until(ExpectedConditions.invisibilityOf(formModal));
                assertTrue(isFormClosed, "Form submitted successfully and closed.");
            } catch (org.openqa.selenium.TimeoutException e) {
                assertFalse(true, "Form submission failed (form did not close).");
            }
        }
    }
}

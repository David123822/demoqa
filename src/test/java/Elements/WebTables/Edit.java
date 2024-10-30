package Elements.WebTables;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.*;

public class Edit {

    WebDriver driver;
    String url = "https://demoqa.com/webtables";


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

    // #app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4)
    @Test
    public void edit(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // we add the contact that we want
        AddForm add = new AddForm(driver);

        add.addForm("name","lname", "email@test.com", "23","45","IT",false);

        // click on the edit button
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#edit-record-4")));
        editButton.click();

        // in the form that opens we will first colect the old department name, then clean the fields then add new name
        WebElement department = wait.until(ExpectedConditions.elementToBeClickable(By.id("department")));

        String oldDep = department.getText();
        department.clear();
        department.sendKeys("Test");

        // now we click save
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submit.click();

        // now we check if the change took place
        WebElement check = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4) > div > div:nth-child(6)")));
        String newDep = check.getText();

        assertNotSame(newDep, oldDep);

    }
}

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

import static org.testng.AssertJUnit.assertTrue;

public class Delete {
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

    @Test
    public void delete(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        AddForm add = new AddForm(driver);

        add.addForm("name","lname", "email@test.com", "23","45","IT",false);

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#delete-record-4")));
        deleteButton.click();

        WebElement row = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app > div > div > div > div.col-12.mt-4.col-md-6 > div.web-tables-wrapper > div.ReactTable.-striped.-highlight > div.rt-table > div.rt-tbody > div:nth-child(4)")));

        String data = row.getText();

        assertTrue("There was an issue with deleting the data", data.isBlank());
    }

}

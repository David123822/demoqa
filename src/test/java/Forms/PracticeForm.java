package Forms;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class PracticeForm {

    WebDriver driver;
    WebDriverWait wait;
    Actions action;
    String url ="https://demoqa.com/automation-practice-form";
    JavascriptExecutor jExecute;
    ChromeOptions options;

    @BeforeTest
    public void open_page(){
        options = new ChromeOptions();


        driver = new ChromeDriver();

        jExecute = (JavascriptExecutor) driver;
        driver.get(url);;
        driver.manage().window().maximize();
    }

    //@AfterTest
    public void close_page(){
        if(driver != null){
            driver.quit();
        }
    }

    @Test
    @Parameters({"fname", "lname", "email", "gender", "mnumber", "birthDate", "subject", "hobbie", "picture", "caddress", "state", "city"})
    public void form(@Optional("John")String fname, @Optional("Doe") String lname,
                     @Optional("johndoe@gmail.com") String email,
                     @Optional("f") String gender, @Optional("1234567890") String mnumber,
                     @Optional("15 Nov 2024") String birthDate, @Optional("Maths") String subject,
                     @Optional("reading") String hobbie, @Optional("sampleFile.jpeg") String picture,
                     @Optional("CurrentAddress") String caddress, @Optional("NCR") String state, @Optional("Delhi") String city) throws InterruptedException {

        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        action = new Actions(driver);

        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
        //jExecute.executeScript("arguments[0].scrollIntoView(true)", firstName);
        firstName.sendKeys(fname);

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName")));
        //jExecute.executeScript("arguments[0].scrollIntoView(true)", lastName);
        lastName.sendKeys(lname);

        WebElement emailAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("userEmail")));
        //jExecute.executeScript("arguments[0].scrollIntoView(true)", emailAddress);
        emailAddress.sendKeys(email);

        switch (gender){
            case "m":
                WebElement male = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]/label")));
                //jExecute.executeScript("arguments[0].scrollIntoView(true)", male);
                male.click();
                break;
            case "f":
                WebElement female = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]/label")));
                //jExecute.executeScript("arguments[0].scrollIntoView(true)", female);
                female.click();
                break;
            case "o":
                WebElement other = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[3]/label")));
                //jExecute.executeScript("arguments[0].scrollIntoView(true)", other);
                other.click();
                break;
        }

        WebElement mobileNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("userNumber")));
        //jExecute.executeScript("arguments[0].scrollIntoView(true)", mobileNumber);
        mobileNumber.sendKeys(mnumber);

        WebElement calendar = wait.until(ExpectedConditions.elementToBeClickable(By.id("dateOfBirthInput")));
        //jExecute.executeScript("arguments[0].scrollIntoView(true)", calendar);

        calendar.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        calendar.sendKeys(birthDate);
        //calendar.sendKeys(Keys.TAB);
        lastName.click();
        Thread.sleep(1000);

        WebElement subjects = wait.until(ExpectedConditions.elementToBeClickable(By.id("subjectsInput")));
        jExecute.executeScript("arguments[0].scrollIntoView(true)", subjects);
        subjects.sendKeys(subject);
        subjects.sendKeys(Keys.ENTER);

        switch (hobbie){
            case "sport":
                WebElement sport = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]/label")));
                jExecute.executeScript("arguments[0].scrollIntoView(true)", sport);
                sport.click();
                break;
            case "reading":
                WebElement reading = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]/label")));
                jExecute.executeScript("arguments[0].scrollIntoView(true)", reading);
                reading.click();
                break;
            case "music":
                WebElement music = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]/label")));
                jExecute.executeScript("arguments[0].scrollIntoView(true)", music);
                music.click();
                break;
        }

        Path downloadsFolder = Paths.get(System.getProperty("user.home"), "Downloads");

        File sentFile = new File(downloadsFolder.toFile(), picture);


        WebElement file = wait.until(ExpectedConditions.elementToBeClickable(By.id("uploadPicture")));
        file.sendKeys(sentFile.getAbsolutePath());

        WebElement currentAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("currentAddress")));
        currentAddress.sendKeys(caddress);

        // Open the state dropdown
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("state")));
        dropdownElement.click();

// Select the desired state by visible text
        WebElement stateOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + state + "')]")));
        stateOption.click();

// Open the city dropdown
        WebElement cityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
        cityDropdown.click();

// Select the desired city by visible text
        WebElement cityOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'" + city + "')]")));
        cityOption.click();

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submit.click();

        WebElement completeForm = wait.until(ExpectedConditions.elementToBeClickable(By.className("modal-content")));
        assertTrue(completeForm.isDisplayed());
    }
}

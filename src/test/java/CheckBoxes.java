import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.time.Duration;
import java.util.*;

import static org.testng.AssertJUnit.assertTrue;

public class CheckBoxes {

    WebDriver driver;
    String url = "https://demoqa.com/checkbox";
    WebElement button;

    @BeforeTest
    public void open_the_page(){
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void close_the_page(){
        if (driver != null){
            driver.close();
        }
    }

    @Test
    @Parameters({"expand", "numOfButtons"})
    public void check_boxes(@Optional ("false") Boolean expand, @Optional ("3") Integer numOfButtons){

        if(expand == false) {
            WebElement home = driver.findElement(By.cssSelector("#tree-node > ol > li > span > label > span.rct-checkbox > svg"));

            if(!home.isSelected()){
                home.click();
                WebElement results = driver.findElement(By.id("result"));
                assertTrue(results.isDisplayed());
            }
        }
        else{

            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

            WebElement meniu = driver.findElement(By.cssSelector("#tree-node > div > button.rct-option.rct-option-expand-all"));
            meniu.click();

            WebElement home = driver.findElement(By.cssSelector("#tree-node > ol > li > span > label > span.rct-checkbox > svg"));

            // the desktop menu part
            WebElement desktop = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement notesButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(1) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement commandsButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));

            // the Documents menu part
            WebElement documents = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg"));

            // the Workspace menu

            WebElement workspaceButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement reactButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement angularButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg"));
            WebElement veuButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(3) > span > label > span.rct-checkbox > svg"));

            // the office menu

            WebElement officeButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg"));
            WebElement publicButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement privateButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg"));
            WebElement generalButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(4) > span > label > span.rct-checkbox > svg"));

            // the downloads menu
            WebElement downloadButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > span > label > span.rct-checkbox > svg"));
            WebElement wordButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg"));
            WebElement excelButton = driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg"));

            // Create a list and add elements
            List<WebElement> itemList = new ArrayList<>();
            itemList.add(home);
            itemList.add(desktop);
            itemList.add(notesButton);
            itemList.add(commandsButton);
            itemList.add(documents);
            itemList.add(workspaceButton);
            itemList.add(reactButton);
            itemList.add(angularButton);
            itemList.add(veuButton);
            itemList.add(officeButton);
            itemList.add(publicButton);
            itemList.add(privateButton);
            itemList.add(generalButton);
            itemList.add(downloadButton);
            itemList.add(wordButton);
            itemList.add(excelButton);

            Collections.shuffle(itemList); // Shuffle the list once, outside the loop

            for (int i = 0; i < Math.min(numOfButtons, itemList.size()); i++) {
                try {
                    // Dynamically locate elements from the page to avoid stale references
                    List<WebElement> itemLis = new ArrayList<>();
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(1) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(1) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(1) > ol > li:nth-child(3) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(2) > ol > li:nth-child(2) > ol > li:nth-child(4) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > ol > li:nth-child(1) > span > label > span.rct-checkbox > svg")));
                    itemLis.add(driver.findElement(By.cssSelector("#tree-node > ol > li > ol > li:nth-child(3) > ol > li:nth-child(2) > span > label > span.rct-checkbox > svg")));

                    // Now retrieve the button based on its current index after the shuffle
                    button = itemList.get(i);

                    // Check and click if not selected
                    if (!button.isSelected()) {
                        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", button);
                        button.click();

                        // Verify result is displayed
                        WebElement results = driver.findElement(By.id("result"));
                        assertTrue(results.isDisplayed());
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("Encountered stale element reference exception. Re-fetching element.");
                }
            }



        }




        }
}

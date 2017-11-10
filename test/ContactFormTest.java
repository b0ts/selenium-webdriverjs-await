import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactFormTest {
    static WebDriver driver;

    @BeforeClass
    static public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://www.sweetlightstudios.com/contact/");
        String titleText = driver.getTitle();
        System.out.println("titleText: " + titleText);
    }

    @Test
    public void contactFormButtonTest(){
        fillOutForm();
        driver.findElement(By.cssSelector(".contact-button")).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Formspree"));
        driver.navigate().back(); // come back to Sweet Light Studios
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("South San Francisco"));
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // verify contact article is displayed
    }

    @Test  // This isn't really needed as it is testing the browser ability and not the form
    public void contactFormSpaceBarTest(){ // test if can submit via space bar for compliance
        fillOutForm();
        driver.findElement(By.cssSelector(".contact-button")).sendKeys(Keys.SPACE);
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Formspree"));
        driver.navigate().back(); // come back to Sweet Light Studios
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("South San Francisco"));
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // verify contact article is displayed
    }

    @Test
    public void contactFormSubmitTest(){
        fillOutForm();
        driver.findElement(By.cssSelector("form[action=\"https://formspree.io/rhbourbonnais@yahoo.com\"]")).submit();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Formspree"));
        driver.navigate().back(); // come back to Sweet Light Studios
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("South San Francisco"));
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // verify contact article is displayed
    }

    private void fillOutForm() {
        String testEmail = "testytest@gmail.com";
        String testName = "Mr. Testy Test";
        String radioButtonSelector = "input[value=\"Personal Branding\"]";
        String testPhone = "831-555-1212";
        String testInfo = "I want a new picture for FB";
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // contact article

        // fill out form
        driver.findElement(By.id("formControlsEmail")).sendKeys(testEmail);
        driver.findElement(By.name("name")).sendKeys(testName);
        driver.findElement(By.cssSelector(radioButtonSelector)).click();
        driver.findElement(By.name("phone")).sendKeys(testPhone);
        driver.findElement(By.name("additionalInfo")).sendKeys(testInfo);

        // verify form filled out
        assertEquals("Email Not Correct", testEmail, driver.findElement(By.id("formControlsEmail")).getAttribute("value"));
        assertEquals("Name Not Correct", testName, driver.findElement(By.name("name")).getAttribute("value"));
        assertTrue("Radio Button not selected", driver.findElement(By.cssSelector(radioButtonSelector)).isSelected() );
        assertEquals("Phone Not Correct", testPhone, driver.findElement(By.name("phone")).getAttribute("value"));
        assertEquals("Info Not Correct", testInfo, driver.findElement(By.name("additionalInfo")).getAttribute("value"));
    }

    @AfterClass
    static public void afterClass() {
        driver.quit();
    }
}

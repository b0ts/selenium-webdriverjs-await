import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class AirVisualTest {
    static WebDriver driver;

    @BeforeClass
    static public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://www.sweetlightstudios.com/contact/");
        String titleText = driver.getTitle();
        System.out.println("titleText: " + titleText);
    }

    @Test
    public void airvisualWidgetButtonTest(){
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // contact article
        WebElement widget = driver.findElement(By.name("airvisual_widget"));
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(widget));
        widget.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("AirVisual - Real-time air quality"));
        driver.navigate().back(); // come back to Sweet Light Studios
        new WebDriverWait(driver,10).until(ExpectedConditions.titleContains("South San Francisco"));
        driver.findElement(By.cssSelector("article.contact")).isDisplayed(); // verify contact article is displayed
    }

    @AfterClass
    static public void afterClass() {
        driver.quit();
    }
}

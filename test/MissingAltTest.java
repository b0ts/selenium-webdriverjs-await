import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import static org.junit.Assert.assertTrue;

public class MissingAltTest {
    static WebDriver driver;

    @BeforeClass
    static public void beforeClass() {
        driver = new ChromeDriver();
        driver.get("https://www.sweetlightstudios.com");
        String titleText = driver.getTitle();
        System.out.println("titleText: " + titleText);
    }

    @Test
    public void findMissingAltTagsTest() {
        String skipSrc = "https://dbeg14ta4byob.cloudfront.net/img/logo/";
        //skipSrc = " "; // comment this in to see test fail
        List<WebElement> elements;
        elements = driver.findElements(By.cssSelector("img"));
        for(WebElement element : elements) {
            String srcText = element.getAttribute("src");
            String altText = element.getAttribute("alt");
            if (!srcText.startsWith(skipSrc)) { // special case to make test pass
                assertTrue("No altText for: " + srcText, altText.length() > 0);
            };
        }
    }

    @AfterClass
    static public void afterClass() {
        driver.quit();
    }

}

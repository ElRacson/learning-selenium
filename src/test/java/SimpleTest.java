import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SimpleTest {

    private static final Logger logger = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    public void mySimpleTest() {

        logger.info("Initializing ChromeDriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3_000));

        // get method
        logger.info("Opening URL: https://www.google.com");
        driver.get("https://www.google.com");

        logger.info("Accept cookies");
        driver.findElement(By.id("L2AGLb")).click();

        logger.info("Searching for acodemy.lv");
        driver.findElement(By.name("q")).sendKeys("acodemy.lv");


        logger.info("Performing search");
        // Wait for element
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("btnI")));
        driver.findElement(By.name("btnK")).click();

        logger.info("Validate search result count");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        List<WebElement> results = driver.findElements(By.xpath("//h3[contains(@class,'LC20lb')]"));
        Assert.assertEquals(results.size(), 16);

        // Close browser
        logger.info("Closing ChromeDriver");
        driver.close();
        driver.quit();

    }
}

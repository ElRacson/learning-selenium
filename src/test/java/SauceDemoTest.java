import lv.acodemy.page_object.LoginPage;
import lv.acodemy.page_object.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ObjectInputFilter;

public class SauceDemoTest {

    lv.acodemy.utils.Config config = lv.acodemy.utils.Config.readConfig();
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void beforeTest() {
        // Initialize driver;
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSauceDemoSuccessLogin() {

        // 1) URL? -> good practice - get url from configuration file
        // 2) Page Object ->
        driver.get("https://www.saucedemo.com");
        loginPage.authorize("standard_user", "secret_sauce");
        // Assert.assertEquals(productsPage.getProductPageTitle().getText(), "Products");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-test='title']")).getText(), "Products");
    }

    @AfterMethod
    public void afterTest() {
        // Close driver
        driver.close();
        driver.quit();
    }
}

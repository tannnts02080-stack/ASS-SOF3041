package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;
    protected String baseUrl = "http://localhost:8082/ASSGD1";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();  // Selenium Manager tự động lấy chromedriver
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void testRegister() {
        driver.get(baseUrl + "/register"); 
        // TODO: đổi theo trang thật

        String unique = "user" + System.currentTimeMillis();

        driver.findElement(By.id("username")).sendKeys(unique);
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("confirmPassword")).sendKeys("123456");
        driver.findElement(By.id("email")).sendKeys(unique + "@mail.com");

        driver.findElement(By.id("btnRegister")).click();

        Assert.assertTrue(
            driver.getPageSource().toLowerCase().contains("thành công")
            || driver.getPageSource().contains("success")
        );
    }
}

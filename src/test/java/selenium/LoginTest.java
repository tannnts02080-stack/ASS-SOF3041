package selenium;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess() {

        driver.get(baseUrl + "/login");

        // Tìm input theo label "Tài khoản:"
        driver.findElement(By.xpath("//label[contains(text(),'Tài khoản')]/following-sibling::input"))
                .sendKeys("admin");

        // Tìm input theo label "Mật khẩu:"
        driver.findElement(By.xpath("//label[contains(text(),'Mật khẩu')]/following-sibling::input"))
                .sendKeys("123");

        // Nhấn nút Đăng nhập
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();

        // Xác nhận điều hướng vào trang admin
        Assert.assertTrue(driver.getPageSource().contains("Chào mừng Admin"));
    }
}

package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ArticleCRUDTest extends BaseTest {

    // Delay để demo
    private void waitDemo() {
        try { Thread.sleep(1200); } catch (Exception e) {}
    }

    // ============================
    // ⭐ HIGHLIGHT ELEMENT
    // ============================
    private void highlight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].style.border='3px solid red'; arguments[0].style.background='yellow';", 
                element
        );
        try { Thread.sleep(250); } catch (Exception ignored) {}

        js.executeScript(
                "arguments[0].style.border=''; arguments[0].style.background='';",
                element
        );
    }

    // ============================
    // ⭐ CLICK + HIGHLIGHT
    // ============================
    private void clickHighlight(WebElement element) {
        highlight(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitDemo();
    }


    private void login() {

        driver.get(baseUrl + "/login");
        waitDemo();

        WebElement user = driver.findElement(By.xpath("//label[contains(text(),'Tài khoản')]/following-sibling::input"));
        highlight(user);
        user.sendKeys("admin");
        waitDemo();

        WebElement pass = driver.findElement(By.xpath("//label[contains(text(),'Mật khẩu')]/following-sibling::input"));
        highlight(pass);
        pass.sendKeys("123456");
        waitDemo();

        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]"));
        clickHighlight(btn);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(text(),'Hãy chọn tính năng ở menu bên trái')]")
                ));
        waitDemo();
    }

    private String generateId() {
        int num = (int) (Math.random() * 900) + 100;
        return "N" + num;
    }

    @Test
    public void testArticleCRUD() throws InterruptedException {

        login();

        driver.get(baseUrl + "/news");
        waitDemo();

        // ============= CREATE =============
        String newsId = generateId();
        String title = "Tin Selenium " + System.currentTimeMillis();

        WebElement idField = driver.findElement(By.name("id"));
        highlight(idField);
        idField.sendKeys(newsId);
        waitDemo();

        WebElement titleField = driver.findElement(By.name("title"));
        highlight(titleField);
        titleField.sendKeys(title);
        waitDemo();

        WebElement contentField = driver.findElement(By.name("content"));
        highlight(contentField);
        contentField.sendKeys("Nội dung tự động Selenium");
        waitDemo();

        WebElement authorField = driver.findElement(By.name("author"));
        highlight(authorField);
        authorField.clear();
        authorField.sendKeys("admin");
        waitDemo();

        // Chọn category
        try {
            WebElement cate = driver.findElement(By.name("categoryId"));
            highlight(cate);
            cate.click();
            waitDemo();

            WebElement cate2 = driver.findElement(By.xpath("//select[@name='categoryId']/option[2]"));
            highlight(cate2);
            cate2.click();
            waitDemo();
        } catch (Exception ignored) {}

        // Tick Home
        try {
            WebElement home = driver.findElement(By.name("home"));
            highlight(home);
            home.click();
            waitDemo();
        } catch (Exception ignored) {}

        WebElement addBtn = driver.findElement(By.xpath("//button[contains(text(),'Thêm mới')]"));
        clickHighlight(addBtn);

        // Đợi reload danh sách
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//td[contains(text(),'" + newsId + "')]")
                ));
        waitDemo();

        Assert.assertTrue(driver.getPageSource().contains(title));

        // ============= UPDATE =============

        WebElement editBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//td[contains(text(),'" + title + "')]/following-sibling::td/button[contains(normalize-space(),'Sửa')]")
                ));

        highlight(editBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editBtn);
        waitDemo();

        clickHighlight(editBtn);

        WebElement titleInput = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));
        highlight(titleInput);
        titleInput.clear();
        waitDemo();

        String updatedTitle = title + " UPDATED";
        titleInput.sendKeys(updatedTitle);
        waitDemo();

        // Click Cập nhật
        WebElement updateBtn = driver.findElement(By.xpath("//button[contains(text(),'Cập nhật')]"));
        clickHighlight(updateBtn);

        Assert.assertTrue(driver.getPageSource().contains(updatedTitle));

        // ============= DELETE =============

        WebElement deleteBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//td[contains(text(),'" + updatedTitle + "')]/following-sibling::td/button[contains(normalize-space(),'Xóa')]")
                ));

        highlight(deleteBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
        waitDemo();

        clickHighlight(deleteBtn);

        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored){}

        waitDemo();

        Assert.assertFalse(driver.getPageSource().contains(updatedTitle));
    }
}

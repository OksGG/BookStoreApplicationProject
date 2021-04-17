package page;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

@Log
public class LoginPage extends BasePage {

    final By login= xpath("//*[@id=\"login\"]");
    final By user=xpath("//*[@id=\"userName\"]");
    final By password=xpath("//*[@id=\"password\"]");
    final By loginClick=cssSelector("#login");
    final By name=xpath("//*[@id=\"userName-value\"]");
    final By textError=xpath("//*[@id=\"name\"]");
    final String loginName = "Linet";
    final String errorText = "Invalid username or password!";


    public void clickLogin() {
        $(login).click();
    }

    public void inputCorrect() {
        $(user).sendKeys("Linet");
        $(password).sendKeys("Test_123%");
        try {
            $(loginClick).click();
        } catch (Exception e) {
           JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", loginClick);
        }
    }

    public void inputError() {
        $(user).sendKeys("Linet");
        $(password).sendKeys("1234");
        try {
            $(loginClick).click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", loginClick);
        }
    }

    public void check() {
        String text = $(name).getText();
        log.info(text);
        Assert.assertTrue(text.contains(loginName));
    }

    public void checkError() {
        String text =$(textError).getText();
        log.info(text);
        Assert.assertTrue(text.contains(errorText));
    }

}

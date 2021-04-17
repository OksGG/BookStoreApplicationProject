package page;

import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.java.Log;

import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Log
public class BasePage {
    public static WebDriver driver;
    public static Properties prop;
public static String driverPath;
    public static String driverCommonPath = "src/test/resources/driver/";
    public static String driverChrome ="chromedriver.exe";
    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization() {
        WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    public BasePage selenideOpen() {
        Selenide.open (prop.getProperty("url"));
        return this;
    }

    public void executor(WebElement element){
        try { driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
element.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }
@AfterAll
    public void selenideClose() {
        Selenide.closeWindow();

    }
@AfterAll
    public void closeDriver() {
        driver.quit();
    }
}

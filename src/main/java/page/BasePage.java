package page;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.java.Log;

import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


@Log
public class BasePage {
    public static WebDriver driver;
    public static Properties prop;
    public BasePage() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/config/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void settings(String type) throws MalformedURLException{
        if (type.equals("Selenide")) {
            final DesiredCapabilities caps = DesiredCapabilities.chrome();
            caps.setCapability("enableVNC", true);
            RemoteWebDriver driver = new RemoteWebDriver(new URL("http://188.130.155.80:4444/wd/hub"), caps);
            WebDriverRunner.setWebDriver(driver);
        }
        else if (type.equals("Selenium")) {
            Configuration.remote = "http://188.130.155.80:4444/wd/hub";
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1080";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability( "enableVNC", true);
            capabilities.setCapability("enableVideo",true);
            Configuration.browserCapabilities = capabilities;
            capabilities = DesiredCapabilities.chrome();
            try {
                driver = new RemoteWebDriver(new URL("http://188.130.155.80:4444/wd/hub"), capabilities);
            } catch (MalformedURLException e) {
                log.info(" ");
            }

        }
    }

    public static void initialization() throws MalformedURLException {
        settings("Selenium");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }

    public BasePage selenideOpen() throws MalformedURLException {
      settings("Selenide");
        Selenide.open (prop.getProperty("url"));
        return this;
    }

    public void executor(WebElement element){
        try {
element.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
    }

    public void selenideClose() {
        Selenide.closeWindow();

    }

    public void closeDriver() {
        driver.quit();
    }
}

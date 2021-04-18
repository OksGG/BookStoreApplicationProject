package page;

import io.qameta.allure.Allure;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@Log
public class BooksPage extends BasePage {

    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement chooseBook;

    @FindBy(xpath = "//*[contains(text(),'Add To Your Collection')]")
    WebElement addBook;

    @FindBy(xpath = "//*[contains(text(),'Profile')]")
    WebElement goToProfile;

    @FindBy(css = "#delete-record-undefined > svg > path")
    WebElement clickDelete;

    @FindBy(xpath = "//*[@id=\"closeSmallModal-ok\"]")
    WebElement clickOk;

    @FindBy(xpath = "//*[@id=\"login\"]")
    WebElement login;

    @FindBy(xpath = "//*[@id=\"userName\"]")
    WebElement user;

    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement password;

    @FindBy(css = "#login")
    WebElement click;

    List<String> listTitle;
    List<String> title = Arrays.asList("Git Pocket Guide", "Learning JavaScript Design Patterns", "Designing Evolvable Web APIs with ASP.NET", "Speaking JavaScript", "You Don't Know JS", "Programming JavaScript Applications", "Eloquent JavaScript", "Second Edition", "Understanding ECMAScript 6");
    WebDriverWait wait;

    public BooksPage() {
        PageFactory.initElements(driver, this);
    }

    public void login() {
        login.click();
        user.sendKeys("Linet");
        password.sendKeys("Test_123%");
        executor(click);
    }

    public void loginCheck() {
        Allure.addAttachment("Console log: ", "test");
        String text = driver.findElement(By.xpath("//*[@id=\"login\"]")).getText();
        log.info(text);
        Assert.assertTrue(text.contains("Login"));
    }

    List<String> getListOfTitle() {
        return driver.findElements(By.className("mr-2"))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void checkListBooks() {
        listTitle = getListOfTitle();
        log.info(String.valueOf(listTitle));
        assertThat(String.valueOf(listTitle)).isEqualTo(String.valueOf(title));
    }

    public void accept() {
        wait = new WebDriverWait(driver, 3000);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void addBook() {
        executor(chooseBook);
        executor(addBook);
        accept();
        executor(goToProfile);
        String bookText = driver.findElement(By.className("mr-2")).getText();
        log.info(bookText);
        assertThat(bookText.equals("Git Pocket Guide"));
    }

    public void deleteBook() {
        clickDelete = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(clickDelete));
        clickDelete.click();
        executor(clickOk);
        log.info("Ок нажата");
        accept();
        String title = driver.findElement(By.className("rt-noData")).getText();
        log.info(title);
        assertThat(title.equals("No rows found"));
    }

}

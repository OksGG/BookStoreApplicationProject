package tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import org.junit.After;

import page.LoginPage;

import java.net.MalformedURLException;

public class CucumberLoginTest extends LoginPage {
    LoginPage loginPage;
@Step("test")
    @Given("^Открываем страницу$")
    public void open() throws MalformedURLException {
        selenideOpen();
        loginPage = new LoginPage();
        loginPage.clickLogin();
    }

    @When("Вводим корректные логин и пароль")
    public void login() {
        loginPage.input("Test1", "Test_123%");
    }

    @When("Вводим некорректные логин и пароль")
    public void loginError() {
        loginPage.input("Test1", "Test_1");
    }

    @Then("Проверяем, что залогинились")
    public void getLoginSuccess() {
        loginPage.check();

    }

    @Then("Получаем ошибку при входе")
    public void getLoginError() {
        loginPage.checkError();

    }

    @After
    @And("Закрываем браузер")
    public void close() {
        selenideClose();
    }


}

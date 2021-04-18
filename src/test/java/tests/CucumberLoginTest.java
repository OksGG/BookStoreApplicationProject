package tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import page.LoginPage;

import java.net.MalformedURLException;

public class CucumberLoginTest extends LoginPage {
    LoginPage loginPage;

    @Step("Human-readable test name")
    @Given("Open page")
    public void open() throws MalformedURLException {
        selenideOpen();
        loginPage = new LoginPage();
        loginPage.clickLogin();
    }

    @When("Data input correct")
    public void login() {
        loginPage.input("Test1", "Test_123%");
    }

    @When("Data input incorrect")
    public void loginError() {
        loginPage.input("Test1", "Test_1");
    }

    @Then("Get success")
    public void getLoginSuccess() {
        loginPage.check();

    }

    @Then("Get login error")
    public void getLoginError() {
        loginPage.checkError();

    }

    @After
    @And("Browser close")
    public void close() {
        selenideClose();
    }


}

package tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import page.LoginPage;

public class CucumberLoginTest extends LoginPage {
    LoginPage loginPage;

    @Given("Open page")
    public void open() {
        selenideOpen();
        loginPage = new LoginPage();
        loginPage.clickLogin();
    }

    @When("Data input correct")
    public void login() {
        loginPage.inputCorrect();
    }

    @When("Data input incorrect")
    public void loginError() {
        loginPage.inputError();
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

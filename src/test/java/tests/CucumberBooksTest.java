package tests;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.BooksPage;
import page.LoginPage;

public class CucumberBooksTest extends BooksPage {
    BooksPage booksPage;


    @Given("Open Book page")
    public void open() {
        initialization();
        booksPage = new BooksPage();
    }

    @When("Check logout")
    public void checkLoginOut() {
        booksPage.loginCheck();
    }

    @When("Login")
    public void loginInPage() {
        booksPage.login();
    }

    @Then("Check list books")
    public void getListBook() {
        booksPage.checkListBooks();
    }

    @Then("Add and delete book")
    public void addDeleteBook() {
        booksPage.addBook();
        booksPage.deleteBook();
    }

    @And("Close browser")
    public void closeBrowser() {
        closeDriver();
    }
}

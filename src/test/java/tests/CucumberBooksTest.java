package tests;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import page.BooksPage;

import java.net.MalformedURLException;


public class CucumberBooksTest extends BooksPage {
    BooksPage booksPage;


    @Given("Открываем страницу с книгами")
    public void open() throws MalformedURLException {
        initialization();
        booksPage = new BooksPage();
    }

    @When("Проверяем, что не залогинены")
    public void checkLoginOut() {
        booksPage.loginCheck();
    }

    @When("Логинимся")
    public void loginInPage() {
        booksPage.login();
    }

    @Then("Проверяем список книг")
    public void getListBook() {
        booksPage.checkListBooks();
    }

    @Then("Добавляем и удаляем книгу")
    public void addDeleteBook() {
        booksPage.addBook();
        booksPage.deleteBook();
    }

    @And("Закрываем окно браузера")
    public void closeBrowser() {
        closeDriver();
    }
}

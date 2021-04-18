package tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.Assert;
import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;


@Log
public class BooksApiTest {
    String url = "https://demoqa.com/BookStore/v1/Books";
    String urlSingleBook = "https://demoqa.com/BookStore/v1/Book";
    String responseBody;
    Response response;
    String jsonBodyAdd = "{\n" +
            "  \"userId\": \"e2d6c1df-d38f-4b68-b0aa-fecc503dd4f4\",\n" +
            "  \"collectionOfIsbns\": [\n" +
            "    {\n" +
            "      \"isbn\": \"9781449337711\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    String jsonBodyDel = "{\n" +
            "  \"isbn\": \"9781449337711\",\n" +
            "  \"userId\": \"e2d6c1df-d38f-4b68-b0aa-fecc503dd4f4\"\n" +
            "}";

    String isbn ="?ISBN=9781449337711";

    @Given("Open url")
    public void openUrl() {
        response = get(url);
        String responseBody = response.asString();
        log.info(responseBody);

    }

    @When("Response")
    public void responseStart() {
       responseBody = response.asString();
        log.info(responseBody);
    }
    @Then("Assert")
    public void checkAssert() {
        JsonPath path = new JsonPath(responseBody);
        String key = path.getString("books[0].title");
        log.info(key);
        Assert.assertEquals(key, "Git Pocket Guide");
    }

    @Given("Add book")
    public void addBook() {
        given().auth().preemptive().basic("Test1", "Test_123%")
                .body(jsonBodyAdd)
                .contentType(ContentType.JSON)
                .when()
                .post(url)
                .then()
                .statusCode(201);
    }
@When("Open profile")
public void checkBookInProfile(){
    RestAssured.given().when()
            .get(urlSingleBook+isbn)
            .then()
            .body("isbn",equalTo("9781449337711"));
}
    @Then("Delete book")
    public void deleteApiBook() {
        RestAssured.given().auth().preemptive().basic("Test1", "Test_123%")
                .body(jsonBodyDel)
               .contentType(ContentType.JSON)
                .when()
                .delete(urlSingleBook)
                .then()
                .statusCode(204);
    }

}





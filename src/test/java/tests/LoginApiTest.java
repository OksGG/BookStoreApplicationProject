package tests;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.junit.Assert;

@Log
public class LoginApiTest {
    JSONObject requestBody;
    Response response;
    int statusCode;
    RequestSpecification request;
    String url = "https://demoqa.com/Account/v1/Authorized";

    @Given("Url open")
    public void apiUrlOpen() {
        request = RestAssured.given();
    }

    @When("Success request parameters")
    public void successParameters() {
        request.header("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "Test1");
        requestBody.put("password", "Test_123%");
        request.body(requestBody.toString());
    }

    @When("Error request parameters")
    public void errorParameters() {
        request.header("Content-Type", "application/json");
        requestBody = new JSONObject();
        requestBody.put("userName", "Linet");
        requestBody.put("password", "Test_12");
        request.body(requestBody.toString());
    }

    @Then("Success api test")
    public void apiLogin() {
        response = request.post(url);
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Then("Error api test")
    public void apiLoginError() {
        response = request.post(url);
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }


}

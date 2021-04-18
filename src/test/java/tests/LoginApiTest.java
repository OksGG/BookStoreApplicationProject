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

    @Given("Входим на страницу Login")
    public void apiUrlOpen() {
        request = RestAssured.given();
    }

    @When("Передаем параметры для успешного входа")
    public void successParameters() {
        request.header("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", "Test1");
        requestBody.put("password", "Test_123%");
        request.body(requestBody.toString());
    }

    @When("Передаем некорректные параметры")
    public void errorParameters() {
        request.header("Content-Type", "application/json");
        requestBody = new JSONObject();
        requestBody.put("userName", "Linet");
        requestBody.put("password", "Test_12");
        request.body(requestBody.toString());
    }

    @Then("Успешный вход - код 200")
    public void apiLogin() {
        response = request.post(url);
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Then("Получаем ошибку 404")
    public void apiLoginError() {
        response = request.post(url);
        statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }


}

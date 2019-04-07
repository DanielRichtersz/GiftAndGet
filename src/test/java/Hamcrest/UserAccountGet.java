package Hamcrest;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Hamcrest.UserTestResources.*;
import static io.restassured.RestAssured.given;

public class UserAccountGet {
    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.port = 8080;

        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users");
    }

    @After
    public void reset() {
        given().params("email", USER_EMAIL)
                .when()
                .request("DELETE", "/users");
    }

    @Test
    public void whenGetUser_ThenSuccessful() {
        given().when()
                .request("GET", "/users/" + USER_USERNAME)
                .then()
                .statusCode(202);
    }

    @Test
    public void whenGetIncorrectUser_ThenUnsuccessful() {
        given().when()
                .request("GET", "/users/" + INCORRECT_USER_USERNAME)
                .then()
                .statusCode(404);
    }
}

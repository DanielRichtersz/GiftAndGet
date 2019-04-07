package Hamcrest;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Hamcrest.UserTestResources.*;
import static Hamcrest.UserTestResources.INCORRECT_USER_USERNAME;
import static io.restassured.RestAssured.given;

public class UserAccountLogin {
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
    public void whenLoginUser_ThenSuccessful() {
        given().params("email", USER_EMAIL, "password", USER_PASSWORD)
                .when()
                .request("GET", "/users/login")
                .then()
                .statusCode(202);
    }

    @Test
    public void whenLoginIncorrectEmail_ThenUnsuccessful() {
        given().params("email", INCORRECT_USER_EMAIL, "password", USER_PASSWORD)
                .when()
                .request("GET", "/users/login")
                .then()
                .statusCode(404);
    }

    @Test
    public void whenLoginIncorrectPassword_ThenUnsuccessful() {
        given().params("email", USER_EMAIL, "password", INCORRECT_USER_PASSWORD)
                .when()
                .request("GET", "/users/login")
                .then()
                .statusCode(403);
    }
}

package Hamcrest;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Hamcrest.UserTestResources.*;
import static Hamcrest.UserTestResources.USER_EMAIL;
import static io.restassured.RestAssured.given;

public class UserDelete {

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
    public void whenDeletingUser_ThenSuccessful() {
        given().params("email", USER_EMAIL)
                .when()
                .request("DELETE", "/users")
                .then()
                .statusCode(202);
    }

    @Test
    public void whenDeletingUser_UserNonExistant() {
        given().params("email", INCORRECT_USER_EMAIL)
                .when()
                .request("DELETE", "/users")
                .then()
                .statusCode(404);
    }
}

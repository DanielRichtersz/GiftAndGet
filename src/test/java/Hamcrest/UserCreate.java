package Hamcrest;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Hamcrest.UserTestResources.*;
import static io.restassured.RestAssured.given;

public class UserCreate {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.port = 8080;
    }

    @After
    public void reset() {
        given().params("email", USER_EMAIL)
                .when()
                .request("DELETE", "/users");
    }

    @Test
    public void whenCreatingUser_ThenSuccessful() {
        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void whenCreatingUser_EmptyParameterValues() {
        given().params("email", EMPTY_USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(400);

        given().params("email", USER_EMAIL, "username", EMPTY_USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(400);

        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", EMPTY_USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(400);
    }

    @Test
    public void whenCreatingUser_EmailAlreadyInUse() {
        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(201);

        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(409);
    }

    @Test
    public void whenCreatingUser_UsernameAlreadyInUse() {
        given().params("email", USER_EMAIL, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(201);

        given().params("email", USER_EMAIL_2, "username", USER_USERNAME, "password", USER_PASSWORD)
                .when()
                .request("POST", "/users")
                .then()
                .statusCode(409);
    }
}

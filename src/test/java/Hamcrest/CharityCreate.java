package Hamcrest;

import danielrichtersz.models.Charity;
import danielrichtersz.repositories.CharityRepository;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static Hamcrest.CharityTestResources.*;
import static Hamcrest.UserTestResources.*;
import static Hamcrest.UserTestResources.USER_EMAIL;
import static io.restassured.RestAssured.given;

public class CharityCreate {

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
        given().params("charityName", CHARITY_NAME, "verified", false)
                .when()
                .request("PUT", "/charities/validate");

        given().params("charityName", CHARITY_NAME)
                .when()
                .request("DELETE", "/charities");

        given().params("email", USER_EMAIL)
                .when()
                .request("DELETE", "/users");
    }

    @Test
    public void whenCreatedCharityValidationRequest_ThenSuccessful() {
        given().params(
                "userEmail", USER_EMAIL,
                "charityName", CHARITY_NAME,
                "charityEmail", CHARITY_EMAIL,
                "charityPhonenumber", CHARITY_PHONENUMBER,
                "charityDescription", CHARITY_DESCRIPTION,
                "charityBankAccount", CHARITY_BANKACCOUNT)
                .when()
                .request("POST", "/charities")
                .then()
                .statusCode(201);
    }
}

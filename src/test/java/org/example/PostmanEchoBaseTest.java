package org.example;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoBaseTest {

    protected static RequestSpecification requestSpec;

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }
}

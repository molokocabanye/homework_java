package org.example;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTests extends PostmanEchoBaseTest {

    @Test
    public void testGetMethod() {
        given()
                .spec(requestSpec)
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", containsString("https://postman-echo.com/get"))
                .body("headers", notNullValue());
    }

    @Test
    public void testPostMethod() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("email", "john@example.com");
        requestBody.put("age", 30);

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json", equalTo(requestBody))
                .body("url", equalTo("https://postman-echo.com/post"))
                .body("headers", notNullValue())
                .body("headers.'content-type'", containsString("application/json"));
    }

    @Test
    public void testPutMethod() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 123);
        requestBody.put("title", "Updated Title");
        requestBody.put("completed", true);

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.id", equalTo(123))
                .body("json.title", equalTo("Updated Title"))
                .body("json.completed", equalTo(true))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    public void testPatchMethod() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Patched Name");
        requestBody.put("status", "active");

        given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.name", equalTo("Patched Name"))
                .body("json.status", equalTo("active"))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    public void testDeleteMethod() {
        given()
                .spec(requestSpec)
                .queryParam("id", "456")
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("args.id", equalTo("456"))
                .body("url", containsString("https://postman-echo.com/delete"))
                .body("data", notNullValue());
    }
}
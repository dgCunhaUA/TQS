package org.example;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class TodoTest {

    @Test
    void whenGetAllTodo_thenCheckStatusCode() {
        given()
        .when()
                .get("https://jsonplaceholder.typicode.com/todos")
        .then().assertThat()
                    .statusCode(200);
    }

    @Test
    void whenGetSpecificTodo_thenCheckTitle() {
        String expectedTitle = "et porro tempora";

        given()
        .when()
                .get("https://jsonplaceholder.typicode.com/todos/4")
        .then().assertThat()
                .statusCode(200)
                .and().body("title", equalTo(expectedTitle));
    }

    @Test
    void whenGetAllTodo_ThenCheck_198_199() {
        given()
        .when()
                .get("https://jsonplaceholder.typicode.com/todos")
        .then().assertThat()
                .statusCode(200)
                .and().body("id", hasItems(198, 199));
    }
}

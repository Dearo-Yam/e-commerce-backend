package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {com.example.demo.DemoApplication.class})
public class OrdersControllerIntegrationTest {
    private static String baseURI = "http://localhost:8080/api/orders";
    private String path;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = baseURI;
    }

    @Test
    public void testHello() {
        path = "/hello";
        RequestSpecification request = RestAssured.given();
        Response response = request.get(path);
        Assertions.assertEquals("Hello from Order controller", response.asPrettyString());
    }

//    @Test
//    public void
}

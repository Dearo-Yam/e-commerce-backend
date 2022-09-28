package com.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {com.example.demo.DemoApplication.class})
public class OrdersControllerIntegrationTest {
//    @Test
//    public void test() {
//        RestAssured.baseURI = "http://localhost:8080/api/orders/hello";
//        RequestSpecification request = RestAssured.given();
//        request.get().getBody().prettyPrint();
//    }
}

package com.example.controller;

import com.example.model.Orders;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest(classes = {com.example.demo.DemoApplication.class})
public class OrdersControllerIntegrationTest {
    private static String baseURI = "http://localhost:8083/api/orders";
    private String path;
    private static RequestSpecification request;
    private Response response;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = baseURI;
        request = RestAssured.given();
    }

    @Test
    public void testHello() {
        path = "/hello";
        response = request.get(path);
        Assertions.assertEquals("Hello from Order controller", response.asPrettyString());
    }

    @Test
    public void testGetAllOrders() {
        path = "/all";
        List<Orders> ordersList;
        response = request
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .extract()
                    .response();
        ordersList =  response.jsonPath().getList("$");
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(22, ordersList.size());
    }

    @Test
    public void testGetOrderById() {
        path = "/1";
        response = request.get(path);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("1", response.jsonPath().getString("orderId"));
    }

    @Test
    public void testGetPendingOrders() {
        path = "/pending";
        List<Orders> ordersList;
        response = request
                .when()
                    .get(path)
                .then()
                    .contentType(ContentType.JSON)
                    .extract()
                    .response();
        ordersList =  response.jsonPath().getList("$");
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(21, ordersList.size());
        LinkedHashMap data;
        for(int i = 0; i < ordersList.size(); i++) {
            data = (LinkedHashMap) response.jsonPath().getList("$").get(i);
            Assertions.assertEquals("Pending", data.get("orderStatus"));
        }
    }

    @Test
    public void testGetShippedOrders() {
        path = "/shipped";
        List<Orders> ordersList;
        response = request
                .when()
                .get(path)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
        ordersList =  response.jsonPath().getList("$");
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(1, ordersList.size());
        LinkedHashMap data;
        for(int i = 0; i < ordersList.size(); i++) {
            data = (LinkedHashMap) response.jsonPath().getList("$").get(i);
            Assertions.assertEquals("Shipped", data.get("orderStatus"));
        }
    }


    @Test
    public void testGetOrdersShipped() {
        path = "/shipped/count";
        response = request.get(path);
        String num = response.asString();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("1", num);
    }

//    @Test
//    public void testShipOrderById() {
//        path = "/1/ship";
//        response = request
//                .when()
//                    .put(path)
//                .then()
//                    .extract()
//                    .response();
////        Assertions.assertEquals(201,);
//        Assertions.assertEquals("Shipped", response.jsonPath().getString("orderStatus"));
//    }

//    @Test
//    public void testUpdate() {
//        path = "/update/1/Shipped";
//        response = request
//                .when()
//                    .put(path)
//                .then()
//                    .extract()
//                    .response();
//        Assertions.assertEquals(202, response.getStatusCode());
//        Assertions.assertEquals("Shipped", response.jsonPath().getString("orderStatus"));
//
//        response = request.put("/update/1/Canceled");
//    }

    @Test
    public void testGetOrderDetails() {
        path = "/1/details";
        response = request
                .when()
                    .get(path)
                .then()
                    .extract()
                    .response();
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(response.jsonPath().getString("name"));
    }
}

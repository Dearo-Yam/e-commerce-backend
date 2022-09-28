package com.example;


import com.example.demo.DemoApplication;
import com.example.model.Orders;
import com.example.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = DemoApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrdersControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    OrderService orderService;
    String baseURI = "/api/orders";

    @Test
    public void helloTest() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get(baseURI + "/hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(response.getContentAsString(), "Hello from Order controller");

    }

    @Test
    public void getAllOrdersTest() {
        try {
            List<Orders> ordersList;
            when(ordersList = orderService.getAllOrders()).thenReturn(ordersList);
            this.mockMvc.perform(get(baseURI + "/all"))
                    .andExpect(status().isOk())
                    .andDo(print());
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderByIDTest() throws Exception{
        Orders orders = new Orders(1,1,1,33.3,23,null,null,null, "shipped");
        Mockito.when(orderService.getOrderById(1)).thenReturn(Optional.of(orders));

        mockMvc.perform(get(baseURI + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId", is(1)))
                .andExpect(jsonPath("orderStatus", is("shipped")));
    }

    @Test
    public void getOrdersTest() {
        try {
            List<Orders> ordersList = new ArrayList<>();
            Orders order1 = new Orders(1,1,1,33.3,23,null,null,null, "shipped");
            Orders order2 = new Orders(1,1,1,33.3,23,null,null,null, "pending");
            ordersList.add(order1);
            ordersList.add(order2);
            when(ordersList = orderService.getPendingOrders()).thenReturn(ordersList);
            this.mockMvc.perform(get(baseURI + "/pending").contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[1].orderStatus", is("pending")));
        } catch( Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest() throws Exception{
        this.mockMvc.perform(get(baseURI + "/update/1/shipped"))
                .andExpect(status().isOk());
    }
}

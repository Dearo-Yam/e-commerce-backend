package com.example.Kafka;


import java.io.PrintWriter;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.example.model.Orders;
import com.example.service.OrderService;


@Service
public class KafkaConsumer {
    @Autowired
    OrderService service;

    @KafkaListener(topics = {"wms-order-shipped", "oms-order-create", "oms-order-canceled"}, 
    groupId = "group_JSON", 
    properties = {"enable.auto.commit:false", ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG + ":${spring.kafka.bootstrap-servers}"},
    containerFactory = "orderKafkaListenerFactory"
    )
    public void consume(@Payload Orders data) throws InterruptedException {
        // if ("Shipped".equals(data.getOrderStatus())){
        //     try{
        //         PrintWriter writer = new PrintWriter("LOGGER.txt", "UTF-8");
        //         writer.println("SHIPPED FOUND");
        //         writer.println(data.toString());
        //         writer.close();
        //     }
        //         catch(Exception e){
        //     }
        if ("Shipped".equals(data.getOrderStatus())){
            service.update(data.getOrderId(), data.getOrderStatus());
            
            try{
                PrintWriter writer = new PrintWriter("LOGGER.txt", "UTF-8");
                writer.println("CREATED updated");
                writer.println(data.toString());
                writer.close();
            }
            catch(Exception e){
            }
            return;
        }
        System.out.println("FAILURE FOUND");
        // }
        // else if ("Canceled".equals(data.getOrderStatus())) {
        //     try{
        //         PrintWriter writer = new PrintWriter("LOGGER.txt", "UTF-8");
        //         writer.println("CANCELLED FOUND");
        //         writer.println(data.toString());
        //         writer.close();
        //     }
        //         catch(Exception e){
        //     }
        // }
        // else if ("Pending".equals(data.getOrderStatus())) {
       
        // }
    }
}
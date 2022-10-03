package com.example.kafka;

import com.example.model.Orders;
import com.example.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
public class KafkaConsumer {
    @Autowired
    OrderService service;

    @KafkaListener(topics = {"wms-order-shipped"},
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
                 writer.println("SHIPPED FOUND");
                 writer.println(data.toString());
                 writer.close();
             } catch(Exception e){
                System.out.println(e);
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
        //     try{
        //         PrintWriter writer = new PrintWriter("LOGGER.txt", "UTF-8");
        //         writer.println("CREATED ORDER");
        //         writer.println(data.toString());
        //         writer.close();
        //     }
        //     catch(Exception e){
        //     }
        // }
    }
}

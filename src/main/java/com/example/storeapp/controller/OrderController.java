package com.example.storeapp.controller;

import com.example.storeapp.config.OrderConfig;
import com.example.storeapp.model.OrderInfo;
import com.example.storeapp.service.StoreService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    StoreService storeService;

    @PostMapping("/order")
    public String bookOrder(@RequestBody OrderInfo order){
        order.setOrderNum(UUID.randomUUID().toString());
        order.setOrderStatus("PLACED");
        order.setPaymentStatus("INITIATED");
        template.convertAndSend(OrderConfig.EXCHANGE,OrderConfig.ROUTING_KEY,order);
        template.convertAndSend(OrderConfig.EXCHANGE,OrderConfig.PAYMENT_ROUTING_KEY,order);
        return "Order placed successfully!!";
    }

    @GetMapping("/status/{id}")
    public OrderInfo getStatusOfOrder(@PathVariable("id") int orderId){
        OrderInfo orderInfo = this.storeService.getStatus(orderId).get();
        return orderInfo;
    }
}

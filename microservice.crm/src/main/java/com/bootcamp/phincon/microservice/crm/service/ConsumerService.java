package com.bootcamp.phincon.microservice.crm.service;


import com.bootcamp.phincon.microservice.crm.model.Orders;
import com.bootcamp.phincon.microservice.crm.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Service

public class ConsumerService {


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JmsTemplate jmsTemplate;


    @JmsListener(destination = "queue.crm.register")
    public void receive(Message<Orders> object) {
        log.info("Received " + object);
        createorder(object.getPayload()).subscribe();

    }


    public Mono<Orders> createorder(Orders orders) {

        return Mono.just(orders)
                .doOnNext(order -> {
                    order.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
                    order.setResponse("Completed");
                })
                .flatMap(orderRepository::save)
                .doOnSuccess(savedOrder -> {
                    jmsTemplate.convertAndSend("queue.status", "Success");
                    log.info("Save order: " + savedOrder);
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Failed to save order: " + e.getMessage());
                    jmsTemplate.convertAndSend("queue.status", "Failed");
                    jmsTemplate.convertAndSend("queue.crm.register", orders);
                    return Mono.just(orders);
                });


    }


}

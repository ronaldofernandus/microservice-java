package com.bootcamp.phincon.orderOrchestration.service;


import com.bootcamp.phincon.orderOrchestration.model.Orders;

import com.bootcamp.phincon.orderOrchestration.model.Steps;
import com.bootcamp.phincon.orderOrchestration.repository.OrderRepository;
import com.bootcamp.phincon.orderOrchestration.repository.StepsRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Service

public class ConsumerService {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StepsRepository stepsRepository;


    Mono<String> status = Mono.just("Success");

    @JmsListener(destination = "queue.status")
    public void recieveStatus(Message<String> message) {

        status = Mono.just(message.getPayload());

    }
    @JmsListener(destination = "queue.order")
    public void sendRegister(Message<Orders> ordersMessage) {
        Flux<Steps> stepsFlux = getAction(ordersMessage.getPayload().getActionId());
log.info("Order Received " + ordersMessage.getPayload());
        stepsFlux.flatMap(steps -> {
            return status.filter("Success"::equals).map(statusValue -> {
                step(steps, ordersMessage.getPayload());
                return steps;
            }).switchIfEmpty(Mono.empty());
        }).subscribe();
    }

    private Flux<Steps> getAction(Long actionId) {
        return stepsRepository.findByActionId(actionId);

    }

    Mono<Void> step(Steps steps, Orders orders) {

        if (steps.getStep().equals("queue.complete")) {
            orders.setResponse("Order Completed");
            sendQueue(steps.getStep(), orders).subscribe();

        }
        sendQueue(steps.getStep(), orders).subscribe();
        return Mono.empty();

    }

    public Mono<Orders> sendQueue(String steps, Orders orders) {
        log.info("Order Process {}" + orders.getResponse());
        jmsTemplate.convertAndSend(steps, orders);
        return Mono.just(orders);
    }


}

package com.bootcamp.phincon.microservice.notif.service;

import com.bootcamp.phincon.microservice.notif.model.Notif;

import com.bootcamp.phincon.microservice.notif.model.Orders;
import com.bootcamp.phincon.microservice.notif.repository.NotifRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service

public class ConsumerService {


    @Autowired
    NotifRepository notifRepository;
    @Autowired
    JmsTemplate jmsTemplate;
    @JmsListener(destination = "queue.notif")
    public void receive(Message<Orders> order) {

        createNotif(new Notif(UUID.randomUUID().toString(),
                order.getPayload().getId(),
                order.getPayload().getResponse(),
                Timestamp.from(Instant.now())))
                .subscribe();
    }


    public Mono<Notif> createNotif(Notif notif) {

        try {
            notif.setStatus("Completed");

            Mono<Notif> sendNotif = notifRepository.save(notif);

            jmsTemplate.convertAndSend("queue.status", "Success");

            return sendNotif;

        } catch (Exception e) {
            jmsTemplate.convertAndSend("queue.status", "Failed");
            jmsTemplate.convertAndSend("queue.notif", notif);
            return Mono.just(notif);
        }


    }
}

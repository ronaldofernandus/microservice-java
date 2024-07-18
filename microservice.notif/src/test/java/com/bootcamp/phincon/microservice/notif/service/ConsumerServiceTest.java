package com.bootcamp.phincon.microservice.notif.service;
import com.bootcamp.phincon.microservice.notif.model.Notif;
import com.bootcamp.phincon.microservice.notif.model.Orders;
import com.bootcamp.phincon.microservice.notif.repository.NotifRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {
    @Mock
    private NotifRepository notifRepository;

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private ConsumerService consumerService;

    @Test
    void receive() {
        // Arrange
        Orders orders = new Orders();
        Message<Orders> orderMessage = mock(Message.class);
        when(orderMessage.getPayload()).thenReturn(orders);


        when(notifRepository.save(any(Notif.class))).thenReturn(Mono.just(new Notif(UUID.randomUUID().toString(), 1L, "response", Timestamp.from(Instant.now()))));


        consumerService.receive(orderMessage);


        verify(notifRepository, times(1)).save(any(Notif.class));
        verify(jmsTemplate, times(1)).convertAndSend(eq("queque.complete"), eq("Success"));
        verify(jmsTemplate, never()).convertAndSend(eq("queque.complete"), eq("Failed"));
    }

    @Test
    void createNotif() {

        Notif notif = new Notif(UUID.randomUUID().toString(), 1L, "response", Timestamp.from(Instant.now()));
        when(notifRepository.save(any(Notif.class))).thenReturn(Mono.just(notif));


        Mono<Notif> result = consumerService.createNotif(notif);


        StepVerifier.create(result)
                .expectNextMatches(savedNotif -> savedNotif.getStatus().equals("Completed"))
                .verifyComplete();

        verify(jmsTemplate, times(1)).convertAndSend(eq("queque.complete"), eq("Success"));
        verify(jmsTemplate, never()).convertAndSend(eq("queque.complete"), eq("Failed"));
    }
}
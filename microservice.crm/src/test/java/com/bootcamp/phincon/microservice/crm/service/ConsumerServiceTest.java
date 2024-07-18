package com.bootcamp.phincon.microservice.crm.service;

import com.bootcamp.phincon.microservice.crm.model.Orders;
import com.bootcamp.phincon.microservice.crm.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ConsumerServiceTest {
    @InjectMocks
    private ConsumerService consumerService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private JmsTemplate jmsTemplate;

    @Test
    void receive() {

        when(orderRepository.save(any())).thenReturn(Mono.just(new Orders()));


        doNothing().when(jmsTemplate).convertAndSend(anyString(), anyString());


        Message<Orders> sampleMessage = createSampleMessage();


        consumerService.receive(sampleMessage);


        verify(orderRepository, times(1)).save(any());


        verify(jmsTemplate, times(1)).convertAndSend("queue.status", "Success");
    }

    @Test
    void createorder() {
        Orders mockOrder = new Orders();
        mockOrder.setResponse("Completed");


        Mockito.when(orderRepository.save(Mockito.any(Orders.class))).thenReturn(Mono.just(mockOrder));


        StepVerifier.create(consumerService.createorder(mockOrder))
                .expectNextMatches(savedOrder -> {

                    return true;
                })
                .verifyComplete();


        Mockito.verify(jmsTemplate, Mockito.times(1)).convertAndSend("queue.status", "Success");
    }

    private Message<Orders> createSampleMessage() {
        Orders sampleOrders = new Orders(); // Create a sample Orders object

        return new org.springframework.messaging.support.GenericMessage<>(sampleOrders);
    }
}


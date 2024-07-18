package com.bootcamp.phincon.orderOrchestration.service;

import com.bootcamp.phincon.orderOrchestration.model.Orders;
import com.bootcamp.phincon.orderOrchestration.model.Steps;
import com.bootcamp.phincon.orderOrchestration.repository.OrderRepository;
import com.bootcamp.phincon.orderOrchestration.repository.StepsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
class ConsumerServiceTest {
    @Mock
    private JmsTemplate jmsTemplate;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private StepsRepository stepsRepository;

    @InjectMocks
    private ConsumerService consumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void recieveStatus() {
        consumerService.recieveStatus(MessageBuilder.withPayload("Success").build());

        StepVerifier.create(consumerService.status)
                .expectNext("Success")
                .verifyComplete();
    }

    @Test
    void sendRegister() {
        Orders orders = new Orders();
        orders.setActionId(1L);

        Steps steps = new Steps();
        steps.setStep("queue.complete");

        Flux<Steps> stepsFlux = Flux.just(steps);

        when(stepsRepository.findByActionId(anyLong())).thenReturn(stepsFlux);

        // Use doNothing for void methods
        doNothing().when(jmsTemplate).convertAndSend(anyString(), (Object) any());

        consumerService.sendRegister(MessageBuilder.withPayload(orders).build());

        verify(consumerService, times(1)).sendQueue(anyString(), any());

        StepVerifier.create(Mono.just(orders.getResponse()))
                .expectNext("Order Completed")
                .verifyComplete();
    }

    @Test
    void step() {
        Steps steps = new Steps();
        steps.setStep("queue.complete");

        Orders orders = new Orders();
        orders.setResponse("Initial Response");

        consumerService.step(steps, orders);

        assertEquals("Order Completed", orders.getResponse());
    }

    @Test
    void sendQueue() {
        Orders orders = new Orders();
        orders.setResponse("Initial Response");

        consumerService.sendQueue("queue.complete", orders);

        verify(jmsTemplate, times(1)).convertAndSend(eq("queue.complete"), eq(orders));
    }
}
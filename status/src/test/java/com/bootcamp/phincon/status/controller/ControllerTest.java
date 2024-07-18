package com.bootcamp.phincon.status.controller;

import com.bootcamp.phincon.status.model.Orders;
import com.bootcamp.phincon.status.service.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ControllerTest {

    @Mock
    private StatusService statusService;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById_Success() {
        // Mock the behavior of statusService.findById
        Orders expectedOrders = new Orders(); // Set up your expected Orders object
        when(statusService.findById(anyLong())).thenReturn(Mono.just(expectedOrders));

        // Invoke the controller method
        Mono<Orders> result = controller.getById(1L);

        // Verify the result using reactive assertions
        StepVerifier.create(result)
                .expectNext(expectedOrders)
                .verifyComplete();
    }

    @Test
    void testGetById_NotFound() {
        // Mock the behavior of statusService.findById for a scenario where the order is not found
        when(statusService.findById(anyLong())).thenReturn(Mono.empty());

        // Invoke the controller method
        Mono<Orders> result = controller.getById(2L);

        // Verify the result using reactive assertions
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    // Add more test cases as needed, including error scenarios

}
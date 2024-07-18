package com.bootcamp.phincon.status.service;

import com.bootcamp.phincon.status.model.Orders;
import com.bootcamp.phincon.status.repository.OrderActionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatusServiceImplTest {

    @Mock
    private OrderActionRepository repository;

    @InjectMocks
    private StatusServiceImpl statusService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_Success() {
        // Mock the behavior of repository.getById
        Orders expectedOrders = new Orders(); // Set up your expected Orders object
        when(repository.getById(anyLong())).thenReturn(Mono.just(expectedOrders));

        // Invoke the service method
        Mono<Orders> result = statusService.findById(1L);

        // Verify the result using reactive assertions
        StepVerifier.create(result)
                .expectNext(expectedOrders)
                .verifyComplete();
    }

    @Test
    void testFindById_NotFound() {
        // Mock the behavior of repository.getById for a scenario where the order is not found
        when(repository.getById(anyLong())).thenReturn(Mono.empty());

        // Invoke the service method
        Mono<Orders> result = statusService.findById(2L);

        // Verify the result using reactive assertions
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

    // Add more test cases as needed, including error scenarios

}
package com.bootcamp.phincon.order.controller;

import com.bootcamp.phincon.order.model.OrderDTO;
import com.bootcamp.phincon.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;



@WebFluxTest (Controller.class)

class ControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private OrderService orderService;

    @Test
    void save() {
        // Mock data
        OrderDTO mockOrderDTO = new OrderDTO(/* provide necessary values */);
        when(orderService.save(mockOrderDTO)).thenReturn(Mono.just(mockOrderDTO));

        // Perform the HTTP request and verify the response
        webTestClient.post()
                .uri("/order/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(mockOrderDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderDTO.class)
                .isEqualTo(mockOrderDTO);
    }

}
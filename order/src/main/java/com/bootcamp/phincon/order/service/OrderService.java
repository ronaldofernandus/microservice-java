package com.bootcamp.phincon.order.service;

import com.bootcamp.phincon.order.model.OrderDTO;
import com.bootcamp.phincon.order.model.Orders;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service

public interface OrderService {
    Mono<OrderDTO> save(OrderDTO orderDTO);

}

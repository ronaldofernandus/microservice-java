package com.bootcamp.phincon.order.controller;

import com.bootcamp.phincon.order.model.OrderDTO;

import com.bootcamp.phincon.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;


import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;


@RestController
@Slf4j
public class Controller {

    @Autowired
    OrderService orderService;

    @PostMapping("/order/save")
    public Mono<OrderDTO> save(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }


}

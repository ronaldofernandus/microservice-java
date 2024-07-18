package com.bootcamp.phincon.status.controller;

import com.bootcamp.phincon.status.model.Notif;
import com.bootcamp.phincon.status.model.Orders;
import com.bootcamp.phincon.status.repository.OrderActionRepository;
import com.bootcamp.phincon.status.service.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class Controller {

    @Autowired
    StatusService statusService;




    @GetMapping ("/getData/{id}")
    public Mono<Orders> getById(@PathVariable("id") Long id) {
        return statusService.findById(id) ;
    }

}

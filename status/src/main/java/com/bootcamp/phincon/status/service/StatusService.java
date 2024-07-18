package com.bootcamp.phincon.status.service;


import com.bootcamp.phincon.status.model.Notif;
import com.bootcamp.phincon.status.model.Orders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service

public interface StatusService {

    Mono<Orders> findById( Long id);
}

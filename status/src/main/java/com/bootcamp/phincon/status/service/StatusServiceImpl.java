package com.bootcamp.phincon.status.service;


import com.bootcamp.phincon.status.model.Orders;

import com.bootcamp.phincon.status.repository.OrderActionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;



@Service
@Slf4j

public class StatusServiceImpl implements StatusService {
    @Autowired
    OrderActionRepository repository;

    @Override
    public Mono<Orders> findById(Long id) {
        Mono<Orders> getOrder=repository.getById(id);
        return getOrder;
    }
}

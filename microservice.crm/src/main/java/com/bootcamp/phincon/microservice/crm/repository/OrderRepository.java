package com.bootcamp.phincon.microservice.crm.repository;


import com.bootcamp.phincon.microservice.crm.model.Orders;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends R2dbcRepository<Orders,Long> {

}

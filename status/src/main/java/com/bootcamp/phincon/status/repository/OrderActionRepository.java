package com.bootcamp.phincon.status.repository;


import com.bootcamp.phincon.status.model.Orders;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository

public interface OrderActionRepository extends R2dbcRepository<Orders,Long> {
    @Query("SELECT * FROM orders WHERE id = :id ")
    Mono<Orders> getById(Long id);
}

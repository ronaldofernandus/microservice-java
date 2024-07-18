package com.bootcamp.phincon.order.repository;

import com.bootcamp.phincon.order.model.Orders;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderActionRepository extends R2dbcRepository<Orders,Long> {
}

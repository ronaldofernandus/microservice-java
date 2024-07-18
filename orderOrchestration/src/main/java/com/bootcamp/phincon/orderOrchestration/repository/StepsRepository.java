package com.bootcamp.phincon.orderOrchestration.repository;

import com.bootcamp.phincon.orderOrchestration.model.Steps;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface StepsRepository extends R2dbcRepository<Steps,Long> {

    @Query("SELECT * FROM steps WHERE action_id = :actionId ORDER BY priority")
    Flux<Steps> findByActionId(Long actionId);
}

package com.bootcamp.phincon.microservice.notif.repository;


import com.bootcamp.phincon.microservice.notif.model.Notif;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NotifRepository extends R2dbcRepository<Notif,String> {
}

package com.bootcamp.phincon.microservice.notif.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table(name="notifs")



@Entity

public class Notif {
    @Id
    String id;
    Long orderId;
    String status;

    Timestamp createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Notif(String id, Long orderId, String status, Timestamp createDDate) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createDate = createDDate;
    }

    public Notif(){

    }





}

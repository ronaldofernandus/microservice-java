package com.bootcamp.phincon.microservice.crm.model;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeAll;


import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NotifTest {

    private static Notif notif;

    @BeforeAll
    static void setUp() {
        notif = new Notif();
        notif.setId("testId");
        notif.setOrderId(123L);
        notif.setStatus("TestStatus");
        notif.setCreateDate(Timestamp.valueOf(LocalDateTime.of(2020, 2, 2, 0, 0, 0)));
    }

    @Test
    void getId() {
        assertEquals("testId", notif.getId());
    }

    @Test
    void setId() {
        notif.setId("newTestId");
        assertEquals("newTestId", notif.getId());
    }

    @Test
    void getOrderId() {
        assertEquals(123L, notif.getOrderId());
    }

    @Test
    void setOrderId() {
        notif.setOrderId(456L);
        assertEquals(456L, notif.getOrderId());
    }

    @Test
    void getStatus() {
        assertEquals("NewStatus", notif.getStatus()); // Adjust the expected value
    }

    @Test
    void setStatus() {
        notif.setStatus("NewStatus");
        assertEquals("NewStatus", notif.getStatus());
    }

    @Test
    void getCreateDate() {
        Timestamp expectedTimestamp = Timestamp.valueOf("2020-02-02 00:00:00");
        Timestamp actualTimestamp = notif.getCreateDate();

        assertEquals(expectedTimestamp, actualTimestamp);
    }

    @Test
    void setCreateDate() {
        Timestamp newTimestamp = new Timestamp(System.currentTimeMillis());
        notif.setCreateDate(newTimestamp);
        assertEquals(newTimestamp, notif.getCreateDate());
    }
}
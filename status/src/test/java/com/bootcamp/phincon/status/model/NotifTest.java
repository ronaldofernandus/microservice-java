package com.bootcamp.phincon.status.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class NotifTest {

    private static Notif notif;

    @BeforeAll
    static void setUp() {
        notif = new Notif();
        notif.setId("testId");

        notif.setStatus("TestStatus");

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
    void getStatus() {
        assertEquals("NewStatus", notif.getStatus()); // Adjust the expected value
    }

    @Test
    void setStatus() {
        notif.setStatus("NewStatus");
        assertEquals("NewStatus", notif.getStatus());
    }


}
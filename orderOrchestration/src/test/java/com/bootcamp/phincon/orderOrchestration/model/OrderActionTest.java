package com.bootcamp.phincon.orderOrchestration.model;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class OrderActionTest {
   public static OrderAction orderAction;

    @BeforeAll
    static void setUp() {
        // Initialize any objects or perform setup operations here
        orderAction = new OrderAction();
        orderAction.setId(1L);
        orderAction.setAction("Test Action");
    }

    @Test
    void getId() {
        assertEquals(1L, orderAction.getId());
    }

    @Test
    void setId() {
        orderAction.setId(2L);
        assertEquals(2L, orderAction.getId());
    }

    @Test
    void getAction() {
        assertEquals("Test Action", orderAction.getAction());
    }

    @Test
    void setAction() {
        orderAction.setAction("Test Action");
        assertEquals("Test Action", orderAction.getAction());
    }
}
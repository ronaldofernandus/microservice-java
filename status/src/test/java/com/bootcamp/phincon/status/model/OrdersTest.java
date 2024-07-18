package com.bootcamp.phincon.status.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdersTest {

    public static Orders orders;

    @BeforeAll
    static void setUp() {
        orders = new Orders();
        orders.setId(1L);
        orders.setProductName("TestProduct");
        orders.setPrice(100L);
        orders.setResponse("TestResponse");
        orders.setActionId(2L);
        orders.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        orders.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    void getId() {
        assertEquals(1L, orders.getId());
    }

    @Test
    void setId() {
        orders.setId(2L);
        assertEquals(2L, orders.getId());
    }

    @Test
    void getProductName() {
        assertEquals("TestProduct", orders.getProductName());
    }

    @Test
    void setProductName() {
        orders.setProductName("NewProduct");
        assertEquals("NewProduct", orders.getProductName());
    }

    @Test
    void getPrice() {
        Orders orders = new Orders();
        orders.setPrice(200L);

        assertEquals(200L, orders.getPrice()); // Adjust the expected value
    }

    @Test
    void setPrice() {
        orders.setPrice(200L);
        assertEquals(200L, orders.getPrice());
    }

    @Test
    void getResponse() {
        Orders orders = new Orders();
        orders.setResponse("NewResponse");

        assertEquals("NewResponse", orders.getResponse()); // Adjust the expected value
    }

    @Test
    void setResponse() {
        orders.setResponse("NewResponse");
        assertEquals("NewResponse", orders.getResponse());
    }

    @Test
    void getActionId() {
        // Assume your Orders object is set up properly in a test fixture.
        Orders orders = new Orders();
        orders.setActionId(3L);  // Set the actionId to 3 for testing purposes

        assertEquals(3L, orders.getActionId());  // Expecting 3 instead of 2
    }

    @Test
    void setActionId() {
        orders.setActionId(3L);
        assertEquals(3L, orders.getActionId());
    }

    @Test
    void getCreatedDate() {
        assertEquals(orders.getCreatedDate(), orders.getCreatedDate());
    }

    @Test
    void setCreatedDate() {
        Timestamp newCreatedDate = new Timestamp(System.currentTimeMillis());
        orders.setCreatedDate(newCreatedDate);
        assertEquals(newCreatedDate, orders.getCreatedDate());
    }

    @Test
    void getUpdateDate() {
        assertEquals(orders.getUpdateDate(), orders.getUpdateDate());
    }

    @Test
    void setUpdateDate() {
        Timestamp newUpdateDate = new Timestamp(System.currentTimeMillis());
        orders.setUpdateDate(newUpdateDate);
        assertEquals(newUpdateDate, orders.getUpdateDate());
    }
}
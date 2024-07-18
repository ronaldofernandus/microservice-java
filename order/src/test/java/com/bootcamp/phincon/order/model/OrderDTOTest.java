package com.bootcamp.phincon.order.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class OrderDTOTest {

    private static OrderDTO orderDTO;

    @BeforeAll
    static void setUp() {
        // Initialize the OrderDTO object with test data
        orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setProductName("TestProduct");
        orderDTO.setPrice(100L);
        orderDTO.setActionId(123L);
    }

    @Test
    void getId() {
        assertEquals(1L, orderDTO.getId());
    }

    @Test
    void setId() {
        orderDTO.setId(2L);
        assertEquals(2L, orderDTO.getId());
    }

    @Test
    void getProductName() {
        assertEquals("TestProduct", orderDTO.getProductName());
    }

    @Test
    void setProductName() {
        orderDTO.setProductName("NewProduct");
        assertEquals("NewProduct", orderDTO.getProductName());
    }

    @Test
    void getPrice() {
        assertEquals(200L, orderDTO.getPrice());
    }

    @Test
    void setPrice() {
        orderDTO.setPrice(200L);
        assertEquals(200L, orderDTO.getPrice());
    }

    @Test
    void getActionId() {
        assertEquals(456L, orderDTO.getActionId());
    }

    @Test
    void setActionId() {
        orderDTO.setActionId(456L);
        assertEquals(456L, orderDTO.getActionId());
    }
}
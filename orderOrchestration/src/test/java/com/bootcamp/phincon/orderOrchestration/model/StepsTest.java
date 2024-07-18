package com.bootcamp.phincon.orderOrchestration.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepsTest {
    public static Steps steps;

    @BeforeAll
    static void setUp() {
        steps = new Steps();
        steps.setId(1L);
        steps.setActionId(2L);
        steps.setStep("Test Step");
        steps.setPriority(3L);
    }

    @Test
    void getId() {
        assertEquals(1L, steps.getId());
    }

    @Test
    void setId() {
        steps.setId(4L);
        assertEquals(4L, steps.getId());
    }

    @Test
    void getActionId() {
        assertEquals(2L, steps.getActionId());
    }

    @Test
    void setActionId() {
        steps.setActionId(2L);
        assertEquals(2L, steps.getActionId());
    }

    @Test
    void getStep() {
        assertEquals("Test Step", steps.getStep());
    }

    @Test
    void setStep() {
        steps.setStep("New Step");
        assertEquals("New Step", steps.getStep());
    }

    @Test
    void getPriority() {
        assertEquals(3L, steps.getPriority());
    }

    @Test
    void setPriority() {
        steps.setPriority(3L);
        assertEquals(3L, steps.getPriority());
    }
}
package com.bootcamp.phincon.orderOrchestration.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "steps")



public class Steps implements Persistable<Long> {
    @Id
    Long id;
    Long actionId;
    String step;
    Long priority;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}

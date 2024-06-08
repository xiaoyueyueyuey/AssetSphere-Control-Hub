package com.ach.domain;

public interface DomainEventListener {
    void onEvent(DomainEvent event);
}

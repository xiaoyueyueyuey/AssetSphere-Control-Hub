package com.ach.domain;



public interface DomainEventDispatcher {
    void dispatchNow(EventQueue eventQueue);
}

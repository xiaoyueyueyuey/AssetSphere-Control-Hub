package com.ach.admin.domain.common;


import com.ach.domain.EventQueue;

public interface DomainEventDispatcher {
    void dispatchNow(EventQueue eventQueue);
}

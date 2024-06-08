package com.ach.domain.system.user.event.manager;


import com.ach.domain.DomainEvent;

public class StatusChangeEvent implements DomainEvent {
    private Long userId;
    private Integer status;
}

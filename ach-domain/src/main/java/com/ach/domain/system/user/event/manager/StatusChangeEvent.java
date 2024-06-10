package com.ach.domain.system.user.event.manager;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class StatusChangeEvent implements DomainEvent {
    private Long userId;
    private Integer status;
}

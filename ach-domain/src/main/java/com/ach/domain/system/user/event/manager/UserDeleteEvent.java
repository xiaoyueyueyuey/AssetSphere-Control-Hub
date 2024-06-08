package com.ach.domain.system.user.event.manager;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class UserDeleteEvent implements DomainEvent {
    private Long userId;
}

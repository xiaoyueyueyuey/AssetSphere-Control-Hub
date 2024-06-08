package com.ach.domain.system.user.event.user;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class UserPasswordUpdateEvent implements DomainEvent {
    private Long userId;
    private String password;
}

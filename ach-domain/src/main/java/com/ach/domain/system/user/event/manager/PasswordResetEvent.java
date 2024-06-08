package com.ach.domain.system.user.event.manager;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class PasswordResetEvent implements DomainEvent {
    private Long userId;
    private String password;
}

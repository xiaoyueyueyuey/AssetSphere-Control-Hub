package com.ach.domain.system.role.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class RoleDeleteEvent implements DomainEvent {
    private Long roleId;
}

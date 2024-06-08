package com.ach.domain.system.role.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class RoleStatusUpdateEvent implements DomainEvent {
    private Long roleId;
    private Integer status;
}

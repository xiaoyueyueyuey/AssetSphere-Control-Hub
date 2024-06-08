package com.ach.domain.system.dept.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class DeptDeleteEvent implements DomainEvent {
    private Long deptId;


}

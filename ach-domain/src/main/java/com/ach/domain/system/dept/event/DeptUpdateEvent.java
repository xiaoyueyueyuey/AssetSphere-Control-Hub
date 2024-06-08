package com.ach.domain.system.dept.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class DeptUpdateEvent implements DomainEvent {
    private Long deptId;
    private Long parentId;
    private String deptName;
    private Integer orderNum;
    private String leaderName;
    private String phone;
    private String email;
    private Integer status;


}

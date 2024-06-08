package com.ach.domain.system.log.login.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

import java.util.Date;

@Data
public class LoginLogAddEvent implements DomainEvent {
    private String username;
    private String ipAddress;
    private String loginLocation;
    private String browser;
    private String operationSystem;
    private Integer status;
    private String msg;
    private Date loginTime;


}

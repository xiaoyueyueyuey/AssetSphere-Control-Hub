package com.ach.domain.system.user.event.manager;


import com.ach.domain.DomainEvent;
import lombok.Data;

import java.util.Date;

@Data
public class UserLoginIpAndTimeUpdateEvent implements DomainEvent {
    private Long userId;
    private String loginIp;
    private Date loginDate;
}

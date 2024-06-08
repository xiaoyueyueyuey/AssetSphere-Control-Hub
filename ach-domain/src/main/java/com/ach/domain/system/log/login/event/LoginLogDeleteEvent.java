package com.ach.domain.system.log.login.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

import java.util.List;

@Data
public class LoginLogDeleteEvent implements DomainEvent {
    private List<Long> infoIds;


}

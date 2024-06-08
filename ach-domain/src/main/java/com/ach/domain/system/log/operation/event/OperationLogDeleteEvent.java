package com.ach.domain.system.log.operation.event;


import com.ach.domain.DomainEvent;
import lombok.Data;

import java.util.List;

@Data
public class OperationLogDeleteEvent implements DomainEvent {
    private List<Long> operationIds;
}

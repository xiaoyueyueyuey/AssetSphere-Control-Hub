package com.ach.domain.asset.classification.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class ACDeleteEvent implements DomainEvent {
    private Integer acId;
}

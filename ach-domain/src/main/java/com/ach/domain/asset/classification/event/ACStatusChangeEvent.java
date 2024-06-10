package com.ach.domain.asset.classification.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class ACStatusChangeEvent implements DomainEvent {
    private Integer acId;
    private Boolean status;
}

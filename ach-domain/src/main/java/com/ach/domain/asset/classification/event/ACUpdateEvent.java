package com.ach.domain.asset.classification.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class ACUpdateEvent implements DomainEvent {
    private String acName;
    private String remark;
    private Integer sort;
}

package com.ach.domain.asset.classification.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class ACAddEvent implements DomainEvent {
    private String acName;
    private String remark;
    private Integer sort;
}

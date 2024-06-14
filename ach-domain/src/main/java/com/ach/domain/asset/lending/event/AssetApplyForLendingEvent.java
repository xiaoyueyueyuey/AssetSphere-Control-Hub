package com.ach.domain.asset.lending.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssetApplyForLendingEvent implements DomainEvent {
    private Long assetId;
    private LocalDate lendingTime;
    private LocalDate returnTime;
    private String remark;
}

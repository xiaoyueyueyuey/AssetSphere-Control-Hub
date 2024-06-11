package com.ach.domain.asset.procurement.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetApplyForProcureEvent implements DomainEvent {
    private Integer count;
    private String remark;
    private BigDecimal unitMoney;
    private BigDecimal totalMoney;
}

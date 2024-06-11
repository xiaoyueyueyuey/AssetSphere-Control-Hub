package com.ach.domain.asset.procurement.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetProcurementCancelEvent implements DomainEvent {
    private Long procurementId;
}

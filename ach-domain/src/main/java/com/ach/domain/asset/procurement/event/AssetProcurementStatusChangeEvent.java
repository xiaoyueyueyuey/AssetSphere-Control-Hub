package com.ach.domain.asset.procurement.event;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetProcurementStatusChangeEvent implements DomainEvent {
    private Long procurementId;

    private Boolean procurementStatus;

}

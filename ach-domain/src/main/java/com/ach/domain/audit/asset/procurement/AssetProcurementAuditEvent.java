package com.ach.domain.audit.asset.procurement;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetProcurementAuditEvent implements DomainEvent {
    private Long procurementId;
    private String remark;
    private Byte auditStatus;
}

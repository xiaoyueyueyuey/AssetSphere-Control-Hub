package com.ach.domain.audit.asset.lending;

import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class AssetLendingAuditEvent implements DomainEvent {
    private Long lendingId;
    private Byte auditStatus;

}

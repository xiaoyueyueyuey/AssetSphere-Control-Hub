package com.ach.domain.audit.asset.procurement;

import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AuditAssetProcurementCommand implements Command {
    @Positive
    private Long procurementId;
    private String remark;
    @PositiveOrZero
    private Byte auditStatus;
}

package com.ach.domain.audit.asset.lending;

import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AuditAssetLendingCommand implements Command {
    @Positive
    private Long lendingId;
    @PositiveOrZero
    private Byte auditStatus;

}

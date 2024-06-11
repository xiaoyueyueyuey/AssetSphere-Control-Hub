package com.ach.domain.asset.procurement.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ChangeAssetProcurementStatusCommand implements Command {
    @Positive
    private Long procurementId;

    @NotNull
    private Boolean procurementStatus;

}

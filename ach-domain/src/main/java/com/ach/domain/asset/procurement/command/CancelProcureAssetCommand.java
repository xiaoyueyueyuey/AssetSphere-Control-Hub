package com.ach.domain.asset.procurement.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CancelProcureAssetCommand implements Command {

    @Positive
    private Long procurementId;

}

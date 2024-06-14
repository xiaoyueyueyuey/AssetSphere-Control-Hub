package com.ach.domain.asset.asset.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ChangeAssetStatusCommand implements Command {
    @PositiveOrZero
    private Long assetId;
    @PositiveOrZero
    private Byte status;
}

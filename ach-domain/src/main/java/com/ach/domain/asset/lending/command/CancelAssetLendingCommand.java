package com.ach.domain.asset.lending.command;


import com.ach.domain.Command;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CancelAssetLendingCommand implements Command {

    @Positive
    private Long lendingId;

}

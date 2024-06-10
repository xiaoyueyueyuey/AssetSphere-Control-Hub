package com.ach.domain.asset.lending.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplyForLendingAssetCommand implements Command {

    @Positive
    private Long userId;

    @Positive
    private Long assetId;

    @FutureOrPresent
    private LocalDate loanTime;

    @FutureOrPresent
    private LocalDate returnTime;

    private String remark;

    private Byte auditStatus;
    private Byte returnStatus;


}

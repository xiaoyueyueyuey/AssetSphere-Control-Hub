package com.ach.domain.asset.procurement.command;

import com.ach.domain.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplyForProcureAssetCommand implements Command {

    @Positive
    private Integer count;
    @NotBlank
    private String remark;
    @Positive
    private BigDecimal unitMoney;
    @Positive
    private BigDecimal totalMoney;

}

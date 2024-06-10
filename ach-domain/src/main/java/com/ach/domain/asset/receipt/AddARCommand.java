package com.ach.domain.asset.receipt;

import com.ach.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddARCommand implements Command {
    @NotBlank
    @Size(min = 1, max = 30)
    private String assetName;//资产名称
    @NotBlank
    @Size(min = 1, max = 120)
    private String assetModel;//资产型号
    @PositiveOrZero
    private Long count;//数量
    @NotNull
    private Integer roomId;
    private String remark;
    @PositiveOrZero
    private BigDecimal unitPrice;
    @PositiveOrZero
    private BigDecimal totalPrice;

    @Positive
    private Integer acId;
}

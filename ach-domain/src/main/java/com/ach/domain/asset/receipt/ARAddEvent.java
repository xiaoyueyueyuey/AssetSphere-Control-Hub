package com.ach.domain.asset.receipt;

import com.ach.domain.DomainEvent;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ARAddEvent implements DomainEvent {

    private String assetName;//资产名称
    private String assetModel;//资产型号
    private Long count;//数量
    private Integer roomId;
    private String remark;
    private BigDecimal unitMoney;
    private BigDecimal totalMoney;
    private Integer acId;
}

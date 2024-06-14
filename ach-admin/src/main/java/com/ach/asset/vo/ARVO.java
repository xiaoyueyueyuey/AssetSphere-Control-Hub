package com.ach.asset.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ARVO {
    private Long assetReceiptId;
    private String acName;
    private String assetModel;
    private String assetName;
    private Long count;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String roomName;
    private String creator;
    private BigDecimal unitMoney;
    private BigDecimal totalMoney;


}

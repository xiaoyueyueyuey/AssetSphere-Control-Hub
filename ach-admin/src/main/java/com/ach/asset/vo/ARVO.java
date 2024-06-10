package com.ach.asset.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ARVO {
    private String assetReceiptId;
    private String assetModel;
    private String assetName;
    private Long count;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String roomName;
    private String creator;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private String acName;


}

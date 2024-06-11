package com.ach.asset.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class APVO {
    private Long procurementId;
    private String userName;
    private String remark;
    private Integer count;
    private BigDecimal unitMoney;
    private BigDecimal totalMoney;
    private Boolean procurementStatus;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Byte auditStatus;
    private Boolean status;
    private String auditRemark;
    private LocalDateTime auditTime;
    private String auditUserName;

}

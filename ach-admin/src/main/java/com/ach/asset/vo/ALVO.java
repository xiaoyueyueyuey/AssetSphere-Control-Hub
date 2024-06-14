package com.ach.asset.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ALVO {
    private Long lendingId;
    private Long userId;
    private String userName;
    private Long assetId;
    private String assetName;
    private String assetModel;
    private LocalDate lendingTime;
    private LocalDate returnTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String remark;
    private Byte auditStatus;
    private Boolean returnStatus;
    private LocalDateTime auditTime;
    private String auditUserName;
}

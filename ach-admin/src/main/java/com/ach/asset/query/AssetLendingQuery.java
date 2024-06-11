package com.ach.asset.query;

import lombok.Data;


@Data
public class AssetLendingQuery {
    private Long userId;
    private String userName;
    private Boolean returnStatus;
    private Byte auditStatus;
    private Integer pageNum;
    private Integer pageSize;
}

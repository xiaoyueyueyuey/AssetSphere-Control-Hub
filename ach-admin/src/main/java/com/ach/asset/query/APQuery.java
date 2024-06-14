package com.ach.asset.query;

import lombok.Data;


@Data
public class APQuery {
    private String userName;
    private Boolean procurementStatus;
    private Byte auditStatus;
    public Integer pageNum;
    public Integer pageSize;
}

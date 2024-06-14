package com.ach.asset.query;

import lombok.Data;


@Data
public class ALQuery {
    private String userName;
    private Boolean returnStatus;
    private Byte auditStatus;
    private Integer pageNum;
    private Integer pageSize;
}

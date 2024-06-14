package com.ach.asset.query;

import lombok.Data;


@Data
public class APQuery {
    private Long userId;
    private String userName;
    private Boolean procurementStatus;
    public Integer pageNum;
    public Integer pageSize;
}

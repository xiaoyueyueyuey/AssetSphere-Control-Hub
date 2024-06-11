package com.ach.asset.query;

import lombok.Data;


@Data
public class APQuery {
    private Long userId;
    private String userName;
    private Boolean procurementStatus;
    private Integer pageNum;
    private Integer pageSize;
}

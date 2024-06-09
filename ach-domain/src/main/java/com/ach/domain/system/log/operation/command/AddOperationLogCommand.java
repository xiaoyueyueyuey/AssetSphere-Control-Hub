package com.ach.domain.system.log.operation.command;


import com.ach.domain.Command;
import lombok.Data;

import java.util.Date;

@Data
public class AddOperationLogCommand implements Command {
    private Integer businessType;
    private Integer requestMethod;
    private String requestModule;
    private String requestUrl;
    private String calledMethod;
    private Integer operatorType;
    private Long userId;
    private String username;
    private String operatorIp;
    private String operatorLocation;
    private Long deptId;
    private String deptName;
    private String operationParam;
    private String operationResult;
    private Integer status;
    private String errorStack;
    private Date operationTime;
}

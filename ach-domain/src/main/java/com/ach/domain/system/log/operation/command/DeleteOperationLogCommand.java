package com.ach.domain.system.log.operation.command;

import com.ach.domain.system.Command;
import lombok.Data;

import java.util.List;

@Data
public class DeleteOperationLogCommand implements Command {
    private List<Long> operationIds;
}

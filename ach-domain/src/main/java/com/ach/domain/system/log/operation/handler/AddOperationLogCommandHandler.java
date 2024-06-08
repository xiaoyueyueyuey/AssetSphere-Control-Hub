package com.ach.domain.system.log.operation.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.log.operation.OperationLogModel;
import com.ach.domain.system.log.operation.command.AddOperationLogCommand;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AddOperationLogCommandHandler implements CommandHandler<AddOperationLogCommand> {

    @Override
    public Boolean handle(EventQueue eventQueue, AddOperationLogCommand command) {
        OperationLogModel operationLogModel = new OperationLogModel();
        Boolean handle = operationLogModel.handle(eventQueue, command);
        return handle;
    }
}

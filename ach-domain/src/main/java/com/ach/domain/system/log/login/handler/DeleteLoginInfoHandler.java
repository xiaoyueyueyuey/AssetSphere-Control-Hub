package com.ach.domain.system.log.login.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.log.login.LoginLogModel;
import com.ach.domain.system.log.login.command.DeleteLoginLogCommand;
import org.springframework.stereotype.Component;

@Component
public class DeleteLoginInfoHandler implements CommandHandler<DeleteLoginLogCommand> {

    @Override
    public Boolean handle(EventQueue eventQueue, DeleteLoginLogCommand command) {
        LoginLogModel loginLogModel = new LoginLogModel();
        loginLogModel.handle(eventQueue, command);
        return true;
    }

}

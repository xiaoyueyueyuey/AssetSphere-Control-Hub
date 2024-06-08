package com.ach.domain.system.log.login.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.log.login.LoginLogModel;
import com.ach.domain.system.log.login.command.AddLoginLogCommand;
import org.springframework.stereotype.Component;

@Component
public class AddLoginLogCommandHandler implements CommandHandler<AddLoginLogCommand> {

    @Override
    public Boolean handle(EventQueue eventQueue, AddLoginLogCommand command) {
        LoginLogModel loginLogModel = new LoginLogModel();
        loginLogModel.handle(eventQueue, command);
        return true;
    }
}

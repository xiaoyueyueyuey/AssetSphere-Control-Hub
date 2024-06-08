package com.ach.domain.system.user.handler.manager;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.command.manager.UpdateUserLoginIpAndTimeCommand;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserLoginIpAndTimeCommandHandler implements CommandHandler<UpdateUserLoginIpAndTimeCommand> {
    @Override
    public Boolean handle(EventQueue eventQueue, UpdateUserLoginIpAndTimeCommand command) {

        UserModel userModel = new UserModel();
        return userModel.handle(eventQueue, command);
    }
}

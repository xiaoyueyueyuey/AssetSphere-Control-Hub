package com.ach.domain.system.user.handler.manager;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.UserRepository;
import com.ach.domain.system.user.command.manager.ResetPasswordCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ResetPasswordCommandHandler implements CommandHandler<ResetPasswordCommand> {
    @Resource
    private UserRepository userRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, ResetPasswordCommand command) {
        UserModel userModel = userRepository.findByIdOrError(command.getUserId());
        Boolean handle = userModel.handle(eventQueue, command);
        if (handle) {
            return userRepository.save(userModel) > 0;
        }
        return false;
    }
}

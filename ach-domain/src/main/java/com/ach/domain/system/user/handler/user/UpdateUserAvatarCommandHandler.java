package com.ach.domain.system.user.handler.user;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.UserProfileModel;
import com.ach.domain.system.user.UserRepository;
import com.ach.domain.system.user.command.user.UpdateUserAvatarCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component

public class UpdateUserAvatarCommandHandler implements CommandHandler<UpdateUserAvatarCommand> {
    @Resource
    private UserRepository userRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateUserAvatarCommand command) {
        UserModel model = userRepository.findByIdOrError(command.getUserId());
        UserProfileModel userProfileModel = new UserProfileModel();

        if (model.getUserId() == null) {
            return userProfileModel.handle(eventQueue, command);
        }
        userProfileModel.setUserId(model.getUserId());

        Boolean handle = userProfileModel.handle(eventQueue, command);

        //这里不用修改聚合根，直接返回即可
        return handle;


    }
}

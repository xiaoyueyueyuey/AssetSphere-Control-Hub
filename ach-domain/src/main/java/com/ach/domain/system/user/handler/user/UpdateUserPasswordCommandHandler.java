package com.ach.domain.system.user.handler.user;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.user.SysUserService;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.UserProfileModel;
import com.ach.domain.system.user.UserRepository;
import com.ach.domain.system.user.command.user.UpdateUserPasswordCommand;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateUserPasswordCommandHandler implements CommandHandler<UpdateUserPasswordCommand> {
    private final UserRepository userRepository;
    @Resource(name = "DomainUserServiceImpl")
    private SysUserService sysUserService;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateUserPasswordCommand command) {
        UserModel model = userRepository.findByIdOrError(command.getUserId());
        UserProfileModel userProfileModel = new UserProfileModel();

        if (model.getUserId() == null) {
            return userProfileModel.handle(eventQueue, command);
        }
        BeanUtils.copyProperties(model, userProfileModel);
        String oldPassword = userRepository.getPasswordByUserId(command.getUserId());//获取用户密码
        Boolean matches = sysUserService.matchesPassword(command.getOldPassword(), oldPassword);
        userProfileModel.setNewOldPasswordIsEqual(matches);
        userProfileModel.setPassword(command.getNewPassword());
        Boolean handle = userProfileModel.handle(eventQueue, command);
        if (handle) {
            userRepository.save(userProfileModel);
        }
        return false;


    }
}

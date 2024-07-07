package com.ach.domain.system.user.handler.manager;


import cn.hutool.core.util.StrUtil;
import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.user.UserModel;
import com.ach.domain.system.user.UserRepository;
import com.ach.domain.system.user.command.manager.UpdateUserCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserCommand> {

    @Resource
    private UserRepository userRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateUserCommand command) {
        UserModel userModel = userRepository.findByIdOrError(command.getUserId());
        if (userModel.getUserId() == null) {
            //用户不存在，直接执行即可，聚合会抛出异常
            userModel.handle(eventQueue, command);
        }
        //名字是否唯一,如果名字没有变化，不需要判断是否唯一
        if ((userModel.getUsername().equals(command.getUsername())) || userRepository.checkUsernameIsUnique(command.getUsername(), command.getUserId())) {
            userModel.setUsername(command.getUsername());
            userModel.setUserNameIsUnique(true);
        } else {
            userModel.setUsername(command.getUsername());
            userModel.setUserNameIsUnique(false);
        }
        //邮箱是否唯一,如果邮箱没有变化，不需要判断是否唯一
        if (userModel.getEmail() == null) {//如果之前邮箱为空
            //如果命令里有邮箱
            if (!StrUtil.isEmptyIfStr(command.getEmail())) {
                if (userRepository.checkEmailIsUnique(command.getEmail(), command.getUserId())) {
                    userModel.setEmail(command.getEmail());
                    userModel.setEmailIsUnique(true);
                } else {
                    userModel.setEmail(command.getEmail());
                    userModel.setEmailIsUnique(false);
                }
            } else {
                userModel.setEmailIsUnique(true);
            }
        } else {
            //如果之前邮箱不为空
            if (StrUtil.isEmptyIfStr(command.getEmail())) {
                userModel.setEmailIsUnique(true);
            } else {
                if (userModel.getEmail().equals(command.getEmail())) {
                    userModel.setEmail(command.getEmail());
                    userModel.setEmailIsUnique(true);
                } else {
                    if (userRepository.checkEmailIsUnique(command.getEmail(), command.getUserId())) {
                        userModel.setEmail(command.getEmail());
                        userModel.setEmailIsUnique(true);
                    } else {
                        userModel.setEmail(command.getEmail());
                        userModel.setEmailIsUnique(false);
                    }

                }
            }
        }
//        //电话号码是否唯一
        if (!StrUtil.isEmptyIfStr(command.getPhoneNumber())) {
            if (userRepository.checkPhoneNumberIsUnique(command.getPhoneNumber(), command.getUserId())) {
                userModel.setPhoneNumber(command.getPhoneNumber());
                userModel.setPhoneNumberIsUnique(true);
            } else {
                userModel.setPhoneNumber(command.getPhoneNumber());
                userModel.setPhoneNumberIsUnique(false);
            }
        } else {
            userModel.setPhoneNumberIsUnique(true);
        }
        //部门是否唯一
//        if (ObjectUtil.isNotNull(command.getDeptId())) {
//            if (userRepository.checkDeptIsExist(command.getDeptId())) {
//                userModel.setDeptIsExist(true);
//            } else {
//                userModel.setDeptIsExist(false);
//            }
//        } else {
//            userModel.setDeptIsExist(true);
//        }
        userModel.setDeptIsExist(true);
        //角色是否唯一
//        if (ObjectUtil.isNotNull(command.getRoleId())) {
//            if (userRepository.checkRoleIsExist(command.getRoleId())) {
//                userModel.setRoleIsExist(true);
//            } else {
//                userModel.setRoleIsExist(false);
//            }
//        } else {
//            userModel.setRoleIsExist(true);
//        }
        userModel.setRoleIsExist(true);
        //职位是否存在,这里简化了，直接设置为存在
        userModel.setPostIsExist(true);
//        if (ObjectUtil.isNotNull(command.getPostId())) {
//            if (userRepository.checkPostIsExist(command.getPostId())) {
//                userModel.setPostIsExist(true);
//            } else {
//                userModel.setPostIsExist(false);
//            }
//        } else {
//            userModel.setPostIsExist(true);
//        }
        userModel.setUserId(command.getUserId());
        Boolean handle = userModel.handle(eventQueue, command);
        if (handle) {
            return userRepository.save(userModel) > 0;
        }
        return false;

    }
}

package com.ach.domain.system.userLogin.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.userLogin.CaptchaService;
import com.ach.domain.system.userLogin.LoginService;
import com.ach.domain.system.userLogin.UserLoginModel;
import com.ach.domain.system.userLogin.command.UserLoginCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserLoginCommandHandler implements CommandHandler<UserLoginCommand> {
    @Resource
    private CaptchaService captchaService;
    @Resource
    private LoginService loginService;

    @Override
    public Boolean handle(EventQueue eventQueue, UserLoginCommand command) {
        UserLoginModel userLoginModel = new UserLoginModel();
        captchaService.validateCaptcha(command.getUsername(), command.getCaptchaCode(), command.getCaptchaCodeKey());
        loginService.userAuthenticate(command.getUsername(), command.getPassword());
        userLoginModel.handle(eventQueue, command);
        return true;
    }
}

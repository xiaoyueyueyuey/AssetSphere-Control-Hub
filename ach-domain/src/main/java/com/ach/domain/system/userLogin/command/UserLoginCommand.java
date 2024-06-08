package com.ach.domain.system.userLogin.command;


import com.ach.domain.system.Command;
import lombok.Data;

@Data
public class UserLoginCommand implements Command {
    private String username;
    private String password;
    private String captchaCode;
    private String captchaCodeKey;
}

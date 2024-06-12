package com.ach.domain.system.user.command.manager;


import com.ach.domain.Command;
import lombok.Data;

/**
 * 
 */
@Data
public class ResetPasswordCommand implements Command {

    private Long userId;
    private String password;

}

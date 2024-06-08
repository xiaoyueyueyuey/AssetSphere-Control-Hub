package com.ach.domain.system.user.command.manager;


import com.ach.domain.system.Command;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class ResetPasswordCommand implements Command {

    private Long userId;
    private String password;

}

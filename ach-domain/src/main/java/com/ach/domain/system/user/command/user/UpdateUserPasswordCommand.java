package com.ach.domain.system.user.command.user;


import com.ach.domain.system.Command;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UpdateUserPasswordCommand implements Command {

    private Long userId;
    private String newPassword;
    private String oldPassword;

}

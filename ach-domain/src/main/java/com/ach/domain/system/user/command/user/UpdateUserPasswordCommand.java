package com.ach.domain.system.user.command.user;


import com.ach.domain.Command;
import lombok.Data;

/**
 * 
 */
@Data
public class UpdateUserPasswordCommand implements Command {

    private Long userId;
    private String newPassword;
    private String oldPassword;

}

package com.ach.domain.system.user.command.manager;


import com.ach.domain.Command;
import lombok.Data;

@Data
public class DeleteUserCommand implements Command {

    private Long userId;
}

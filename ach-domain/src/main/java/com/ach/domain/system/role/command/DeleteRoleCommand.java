package com.ach.domain.system.role.command;


import com.ach.domain.system.Command;
import lombok.Data;

@Data
public class DeleteRoleCommand implements Command {
    private Long roleId;
}

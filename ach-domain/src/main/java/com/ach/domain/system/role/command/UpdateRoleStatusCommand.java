package com.ach.domain.system.role.command;


import com.ach.domain.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
public class UpdateRoleStatusCommand implements Command {

    private Long roleId;

    private Integer status;

}

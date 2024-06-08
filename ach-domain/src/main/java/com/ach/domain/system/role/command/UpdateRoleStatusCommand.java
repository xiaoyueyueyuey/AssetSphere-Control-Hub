package com.ach.domain.system.role.command;


import com.ach.domain.system.Command;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class UpdateRoleStatusCommand implements Command {

    private Long roleId;

    private Integer status;

}

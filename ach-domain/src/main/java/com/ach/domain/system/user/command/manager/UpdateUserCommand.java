package com.ach.domain.system.user.command.manager;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserCommand extends AddUserCommand {
    private Long userId;

}

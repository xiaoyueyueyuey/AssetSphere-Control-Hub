package com.ach.domain.system.role.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.role.RoleModel;
import com.ach.domain.system.role.RoleRepository;
import com.ach.domain.system.role.command.DeleteRoleCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DeleteRoleCommandHandler implements CommandHandler<DeleteRoleCommand> {
    @Resource
    private RoleRepository roleRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, DeleteRoleCommand command) {
        RoleModel model = roleRepository.findByIdOrError(command.getRoleId());
        Boolean handle = model.handle(eventQueue, command);
        if (handle) {
            return roleRepository.deleteBatchByIds(Collections.singletonList(model.getRoleId()));
        }
        return false;
    }
}

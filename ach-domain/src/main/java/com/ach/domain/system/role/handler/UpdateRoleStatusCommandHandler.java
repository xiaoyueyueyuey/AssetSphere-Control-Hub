package com.ach.domain.system.role.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.role.RoleModel;
import com.ach.domain.system.role.RoleRepository;
import com.ach.domain.system.role.command.UpdateRoleStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateRoleStatusCommandHandler implements CommandHandler<UpdateRoleStatusCommand> {

    private final RoleRepository roleRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateRoleStatusCommand command) {
        RoleModel model = roleRepository.findByIdOrError(command.getRoleId());
        model.setStatus(command.getStatus());
        Boolean handle = model.handle(eventQueue, command);
        if (handle) {
            return roleRepository.save(model) > 0;
        }
        return false;
    }
}

package com.ach.domain.system.role.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.role.RoleModel;
import com.ach.domain.system.role.RoleRepository;
import com.ach.domain.system.role.command.AddRoleCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddRoleCommandHandler implements CommandHandler<AddRoleCommand> {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AddRoleCommand command) {
        Boolean roleKeyIsUnique = roleRepository.checkRoleKeyIsUnique(command.getRoleKey());
        Boolean roleNameIsUnique = roleRepository.checkRoleNameIsUnique(command.getRoleName());
        RoleModel roleModel = new RoleModel();
        roleModel.setRoleNameIsUnique(roleNameIsUnique);
        roleModel.setRoleKeyIsUnique(roleKeyIsUnique);
        roleModel.setRoleIsAssignToUserCount(0L);
        roleModel.setRoleKey(command.getRoleKey());
        roleModel.setRoleName(command.getRoleName());
        roleModel.setStatus(command.getStatus());
        Boolean handle = roleModel.handle(eventQueue, command);
        if (handle) {
            Long roleId = roleRepository.save(roleModel);
            eventQueue.queue().forEach(domainEvent -> domainEvent.setAggregateId(roleId));
            return roleId > 0;
        }
        return false;
    }
}

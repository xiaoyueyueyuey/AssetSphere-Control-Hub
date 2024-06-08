package com.ach.domain.system.role.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.role.RoleModel;
import com.ach.domain.system.role.RoleRepository;
import com.ach.domain.system.role.command.UpdateRoleDataScopeCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoleDataScopeCommandHandler implements CommandHandler<UpdateRoleDataScopeCommand> {
    @Resource
    private RoleRepository roleRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateRoleDataScopeCommand command) {
        RoleModel model = roleRepository.findByIdOrError(command.getRoleId());
        if (model.getRoleId() == null) {
            return model.handle(eventQueue, command);
        }
        //这个聚合不存数据范围，事件才存
        return model.handle(eventQueue, command);
    }
}

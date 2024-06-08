package com.ach.domain.system.dept.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.dept.DeptModel;
import com.ach.domain.system.dept.DeptRepository;
import com.ach.domain.system.dept.command.UpdateDeptCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UpdateDeptCommandHandler implements CommandHandler<UpdateDeptCommand> {
    @Resource
    private DeptRepository deptRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateDeptCommand command) {
        DeptModel deptModel = deptRepository.findByIdOrError(command.getDeptId());
        if (deptModel.getDeptId() == null) {
            return deptModel.handle(eventQueue, command);
        }
        Boolean b = deptRepository.checkDeptNameIsUnique(command.getDeptName(), command.getDeptId());
        deptModel.setDeptNameIsUnique(b);
        deptModel.setParentId(command.getParentId());
        deptModel.setStatus(command.getStatus());
        Boolean handle = deptModel.handle(eventQueue, command);
        if (handle) {
            return deptRepository.save(deptModel) > 0;
        }
        return false;
    }
}

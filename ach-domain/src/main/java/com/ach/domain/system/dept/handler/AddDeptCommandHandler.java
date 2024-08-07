package com.ach.domain.system.dept.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.DomainEvent;
import com.ach.domain.EventQueue;
import com.ach.domain.system.dept.DeptModel;
import com.ach.domain.system.dept.DeptRepository;
import com.ach.domain.system.dept.command.AddDeptCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加部门命令处理器，处理器统一只给聚合赋聚合里面赋不到的值，不做业务逻辑处理
 */
@Component

public class AddDeptCommandHandler implements CommandHandler<AddDeptCommand> {
    @Resource
    private DeptRepository deptRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, AddDeptCommand command) {
        // 加载部门聚合
        DeptModel deptModel = new DeptModel();
        // 给部门聚合赋命令没有的属性
        // 查询父部门
        Boolean parentDeptIsExist = deptRepository.checkParentDeptIsExist(command.getParentId());
        if (parentDeptIsExist) {
            deptModel.setParentId(command.getParentId());
        }
        Boolean b = deptRepository.checkDeptNameIsUnique(command.getDeptName());
        deptModel.setDeptNameIsUnique(b);
        // 处理命令
        Boolean handle = deptModel.handle(eventQueue, command);
        if (handle) {
            Long deptId = deptRepository.save(deptModel);
            //给队列的命令赋值
            List<DomainEvent> queue = eventQueue.queue();
            queue.forEach(domainEvent -> {
                domainEvent.setAggregateId(deptId);//设置聚合id
            });
            return deptId > 0;
        }
        return false;
    }
}

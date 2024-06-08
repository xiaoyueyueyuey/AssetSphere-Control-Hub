package com.ach.domain.system.menu.handler;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import com.ach.domain.system.menu.MenuModel;
import com.ach.domain.system.menu.MenuRepository;
import com.ach.domain.system.menu.command.DeleteMenuCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DeleteMenuCommandHandler implements CommandHandler<DeleteMenuCommand> {

    @Resource
    private MenuRepository menuRepository;

    /**
     * 删除菜单
     *
     * @param eventQueue
     * @param command
     */
    public Boolean handle(EventQueue eventQueue, DeleteMenuCommand command) {
        MenuModel menuModel = menuRepository.findByIdOrError(command.getMenuId());

        Boolean handle = menuModel.handle(eventQueue, command);
        if (handle) {
            menuRepository.deleteBatchByIds(Collections.singletonList(command.getMenuId()));
        }
        return handle;
    }
}

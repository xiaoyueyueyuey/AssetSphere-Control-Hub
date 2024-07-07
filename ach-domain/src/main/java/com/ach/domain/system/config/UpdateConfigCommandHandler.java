package com.ach.domain.system.config;


import com.ach.domain.CommandHandler;
import com.ach.domain.EventQueue;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UpdateConfigCommandHandler implements CommandHandler<UpdateConfigCommand> {
    @Resource
    private ConfigRepository configRepository;

    @Override
    public Boolean handle(EventQueue eventQueue, UpdateConfigCommand command) {
        ConfigModel model = configRepository.findByIdOrError(Long.valueOf(command.getConfigId()));
        model.setConfigId(command.getConfigId());
        model.setConfigValue(command.getConfigValue());
        model.setConfigOptionSet(model.getConfigOptionSet());
        //聚合就不用保存了,事件会处理
        return model.handle(eventQueue, command);
    }
}

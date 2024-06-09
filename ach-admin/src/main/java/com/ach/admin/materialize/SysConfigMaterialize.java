package com.ach.admin.materialize;

import com.ach.admin.entity.SysConfigEntity;
import com.ach.admin.mapper.SysConfigMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.config.ConfigUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SysConfigMaterialize implements DomainEventListener {
    private final SysConfigMapper sysConfigMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof ConfigUpdateEvent) {
            updateConfig((ConfigUpdateEvent) event);
        }
    }

    private void updateConfig(ConfigUpdateEvent event) {
        SysConfigEntity sysConfigEntity = new SysConfigEntity();
        sysConfigEntity.setConfigId(event.getConfigId());
        sysConfigEntity.setConfigValue(event.getConfigValue());
        sysConfigMapper.updateById(sysConfigEntity);
    }
}

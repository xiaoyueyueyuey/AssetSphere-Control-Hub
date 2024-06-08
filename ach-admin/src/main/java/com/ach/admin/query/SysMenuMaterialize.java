package com.ach.admin.query;

import com.ach.admin.entity.SysMenuEntity;
import com.ach.admin.mapper.SysMenuMapper;
import com.ach.common.utils.jackson.JacksonUtil;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.menu.event.MenuAddEvent;
import com.ach.domain.system.menu.event.MenuDeleteEvent;
import com.ach.domain.system.menu.event.MenuUpdateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SysMenuMaterialize implements DomainEventListener {
    private final SysMenuMapper sysMenuMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof MenuAddEvent) addSysMenu((MenuAddEvent) event);
        else if (event instanceof MenuUpdateEvent) updateSysMenu((MenuUpdateEvent) event);
        else if (event instanceof MenuDeleteEvent) deleteSysMenu((MenuDeleteEvent) event);

    }

    private void addSysMenu(MenuAddEvent event) {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(event, sysMenuEntity);
        String metaInfo = JacksonUtil.to(event.getMeta());
        sysMenuEntity.setMetaInfo(metaInfo);
        sysMenuMapper.insert(sysMenuEntity);

    }

    private void updateSysMenu(MenuUpdateEvent event) {
        SysMenuEntity sysMenuEntity = new SysMenuEntity();
        BeanUtils.copyProperties(event, sysMenuEntity);
        String metaInfo = JacksonUtil.to(event.getMeta());
        sysMenuEntity.setMetaInfo(metaInfo);
        sysMenuMapper.updateById(sysMenuEntity);
    }

    private void deleteSysMenu(MenuDeleteEvent event) {
        sysMenuMapper.deleteById(event.getMenuId());
    }
}

package com.ach.admin.materialize;

import com.ach.admin.entity.SysLoginInfoEntity;
import com.ach.admin.mapper.SysLoginInfoMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.log.login.event.LoginLogAddEvent;
import com.ach.domain.system.log.login.event.LoginLogDeleteEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SysLoginInfoMaterialize implements DomainEventListener {
    final SysLoginInfoMapper sysLoginInfoMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof LoginLogDeleteEvent) {
            deleteLoginInfo((LoginLogDeleteEvent) event);
        } else if (event instanceof LoginLogAddEvent) {
            addLoginInfo((LoginLogAddEvent) event);

        }
    }

    private void addLoginInfo(LoginLogAddEvent event) {
        SysLoginInfoEntity sysLoginInfoEntity = new SysLoginInfoEntity();
        BeanUtils.copyProperties(event, sysLoginInfoEntity);
        sysLoginInfoMapper.insert(sysLoginInfoEntity);
    }

    private void deleteLoginInfo(LoginLogDeleteEvent event) {
        sysLoginInfoMapper.deleteBatchIds(event.getInfoIds());
    }
}

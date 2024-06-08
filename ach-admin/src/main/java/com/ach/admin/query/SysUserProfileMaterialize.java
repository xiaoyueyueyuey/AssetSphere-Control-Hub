package com.ach.admin.query;

import com.ach.admin.entity.SysUserEntity;
import com.ach.admin.mapper.SysUserMapper;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.user.event.user.UserAvatarUpdateEvent;
import com.ach.domain.system.user.event.user.UserPasswordUpdateEvent;
import com.ach.domain.system.user.event.user.UserProfileUpdateEvent;
import com.ach.infrastructure.user.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor

@Component
public class SysUserProfileMaterialize implements DomainEventListener {
    private final SysUserMapper sysUserMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof UserProfileUpdateEvent) {
            updateSysUserProfile((UserProfileUpdateEvent) event);
        } else if (event instanceof UserAvatarUpdateEvent) {
            updateSysUserAvatar((UserAvatarUpdateEvent) event);
        } else if (event instanceof UserPasswordUpdateEvent) {
            updateSysUserPassword((UserPasswordUpdateEvent) event);
        }

    }

    private void updateSysUserPassword(UserPasswordUpdateEvent event) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        AuthenticationUtils.encryptPassword(event.getPassword());
        sysUserEntity.setPassword(event.getPassword());
        sysUserEntity.setUserId(event.getUserId());
        sysUserMapper.updateById(sysUserEntity);
    }

    private void updateSysUserAvatar(UserAvatarUpdateEvent event) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setAvatar(event.getAvatar());
        sysUserEntity.setUserId(event.getUserId());
        sysUserMapper.updateById(sysUserEntity);
    }

    private void updateSysUserProfile(UserProfileUpdateEvent event) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        BeanUtils.copyProperties(event, sysUserEntity);
        sysUserMapper.updateById(sysUserEntity);
    }
}

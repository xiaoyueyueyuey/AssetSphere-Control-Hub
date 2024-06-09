package com.ach.admin.materialize;

import cn.hutool.core.util.StrUtil;
import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.mapper.SysRoleMapper;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.role.event.*;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor

@Component
public class SysRoleMaterialize implements DomainEventListener {
    @Resource
    private final SysRoleMapper sysRoleMapper;

    @Override
    public void onEvent(DomainEvent event) {
        if (event instanceof RoleUpdateEvent) {
            updateRole((RoleUpdateEvent) event);
        } else if (event instanceof RoleAddEvent) {
            addRole((RoleAddEvent) event);
        } else if (event instanceof RoleDeleteEvent) {
            deleteRole((RoleDeleteEvent) event);
        } else if (event instanceof RoleStatusUpdateEvent) {
            updateRoleStatus((RoleStatusUpdateEvent) event);
        } else if (event instanceof RoleDataScopeUpdateEvent) {
            updateRoleDataScope((RoleDataScopeUpdateEvent) event);
        }

    }


    private void updateRoleDataScope(RoleDataScopeUpdateEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        String deptIdSet = generateDeptIdSet(event.getDeptIds());
        sysRoleEntity.setDeptIdSet(deptIdSet);
        sysRoleMapper.updateById(sysRoleEntity);
    }

    private void updateRoleStatus(RoleStatusUpdateEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        sysRoleMapper.updateById(sysRoleEntity);

    }

    private void deleteRole(RoleDeleteEvent event) {
        sysRoleMapper.deleteById(event.getRoleId());
    }

    private void addRole(RoleAddEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        sysRoleMapper.insert(sysRoleEntity);
    }

    private void updateRole(RoleUpdateEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        sysRoleMapper.updateById(sysRoleEntity);
    }

    public String generateDeptIdSet(List<Long> deptIds) {
        if (deptIds == null) {
            return "";
        }
        if (deptIds.size() > new HashSet<>(deptIds).size()) {
            throw new ApiException(ErrorCode.Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT);
        }

        return StrUtil.join(",", deptIds);
    }
}
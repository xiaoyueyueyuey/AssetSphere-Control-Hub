package com.ach.admin.materialize;

import cn.hutool.core.util.StrUtil;
import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.entity.SysRoleMenuEntity;
import com.ach.admin.mapper.SysRoleMapper;
import com.ach.admin.mapper.SysRoleMenuMapper;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.DomainEvent;
import com.ach.domain.DomainEventListener;
import com.ach.domain.system.role.event.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SysRoleMaterialize implements DomainEventListener {
    private final SysRoleMapper sysRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional
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

    public void deleteRole(RoleDeleteEvent event) {
        sysRoleMapper.deleteById(event.getRoleId());
        List<SysRoleMenuEntity> sysRoleMenuEntities = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenuEntity>().eq(SysRoleMenuEntity::getRoleId, event.getRoleId()));
        for (SysRoleMenuEntity sysRoleMenuEntity : sysRoleMenuEntities) {
            sysRoleMenuMapper.deleteById(sysRoleMenuEntity);
        }
    }

    public void addRole(RoleAddEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        System.out.println("sysRoleEntity" + sysRoleEntity);
        sysRoleMapper.insert(sysRoleEntity);
        for (Long menuId : event.getMenuIds()) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setRoleId(event.getRoleId());
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuMapper.insert(sysRoleMenuEntity);
        }
    }
    private void updateRole(RoleUpdateEvent event) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        BeanUtils.copyProperties(event, sysRoleEntity);
        sysRoleMapper.updateById(sysRoleEntity);
        //有bug
        Long roleId = event.getRoleId();
        List<SysRoleMenuEntity> sysRoleMenuEntities = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenuEntity>().eq(SysRoleMenuEntity::getRoleId, roleId));
        for (SysRoleMenuEntity sysRoleMenuEntity : sysRoleMenuEntities) {
            sysRoleMenuMapper.deleteById(sysRoleMenuEntity);
        }
        for (Long menuId : event.getMenuIds()) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setRoleId(roleId);
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuMapper.insert(sysRoleMenuEntity);
        }

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

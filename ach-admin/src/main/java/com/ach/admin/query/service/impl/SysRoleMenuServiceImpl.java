package com.ach.admin.query.service.impl;


import com.ach.admin.entity.SysRoleMenuEntity;
import com.ach.admin.mapper.SysRoleMenuMapper;
import com.ach.admin.query.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity> implements
        SysRoleMenuService {

}

package com.ach.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ach.admin.dto.role.RoleDTO;
import com.ach.admin.dto.user.UserDTO;
import com.ach.admin.entity.SysMenuEntity;
import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.entity.SysUserEntity;
import com.ach.admin.mapper.SysRoleMapper;
import com.ach.admin.query.AllocatedRoleQuery;
import com.ach.admin.query.UnallocatedRoleQuery;
import com.ach.admin.service.SysMenuService;
import com.ach.admin.service.SysRoleService;
import com.ach.admin.service.SysUserService;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * 
 * @since 2022-06-16
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    private final SysMenuService menuService;

    private final SysUserService userService;

    //    @Override
//    public boolean isRoleNameDuplicated(Long roleId, String roleName) {
//        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ne(roleId != null, "role_id", roleId)
//            .eq("role_name", roleName);
//        return this.baseMapper.exists(queryWrapper);
//    }
//    @Override
//    public boolean isRoleKeyDuplicated(Long roleId, String roleKey) {
//        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ne(roleId != null, "role_id", roleId)
//            .eq("role_key", roleKey);
//        return this.baseMapper.exists(queryWrapper);
//    }
//    @Override
//    public boolean isAssignedToUsers(Long roleId) {
//        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id", roleId);
//        return userMapper.exists(queryWrapper);
//    }
    @Override
    public List<SysMenuEntity> getMenuListByRoleId(Long roleId) {
        return baseMapper.getMenuListByRoleId(roleId);
    }

    @Override
    public RoleDTO getRoleInfo(Long roleId) {
        SysRoleEntity byId = this.getById(roleId);
        RoleDTO roleDTO = new RoleDTO(byId);
        List<Long> selectedDeptList = StrUtil.split(byId.getDeptIdSet(), ",")
                .stream().filter(StrUtil::isNotEmpty).map(
                        s -> Long.parseLong(s.trim())
                ).collect(Collectors.toList());
        roleDTO.setSelectedDeptList(selectedDeptList);
        roleDTO.setSelectedMenuList(menuService.getMenuIdsByRoleId(roleId));
        return roleDTO;
    }

    @Override
    public PageCustomDTO<UserDTO> getAllocatedUserList(AllocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageCustomDTO<>(dtoList, page.getTotal());
    }

    @Override
    public PageCustomDTO<UserDTO> getUnallocatedUserList(UnallocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageCustomDTO<>(dtoList, page.getTotal());
    }


}

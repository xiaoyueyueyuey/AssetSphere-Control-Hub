package com.ach.admin.query.service;


import com.ach.admin.dto.role.RoleDTO;
import com.ach.admin.dto.user.UserDTO;
import com.ach.admin.entity.SysMenuEntity;
import com.ach.admin.entity.SysRoleEntity;
import com.ach.admin.query.AllocatedRoleQuery;
import com.ach.admin.query.UnallocatedRoleQuery;
import com.ach.infrastructure.page.PageCustomDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-16
 */
public interface SysRoleService extends IService<SysRoleEntity> {

//
//    /**
//     * 校验角色名称是否唯一
//     * @param roleId 角色Id
//     * @param roleName 角色名称
//     * @return 结果
//     */
//    boolean isRoleNameDuplicated(Long roleId, String roleName);
//
//    /**
//     * 校验角色权限是否唯一
//     * @param roleId 角色Id
//     * @param roleKey 角色的Key
//     * @return 结果
//     */
//    boolean isRoleKeyDuplicated(Long roleId, String roleKey);
//
//
//    /**
//     * 检测角色是否分配给用户
//     *
//     * @param roleId 角色id
//     * @return 校验结果
//     */
//    boolean isAssignedToUsers(Long roleId);

    /**
     * 获取用户的权限列表
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    List<SysMenuEntity> getMenuListByRoleId(Long roleId);


    RoleDTO getRoleInfo(Long roleId);

    PageCustomDTO<UserDTO> getAllocatedUserList(AllocatedRoleQuery query);

    PageCustomDTO<UserDTO> getUnallocatedUserList(UnallocatedRoleQuery query);
}

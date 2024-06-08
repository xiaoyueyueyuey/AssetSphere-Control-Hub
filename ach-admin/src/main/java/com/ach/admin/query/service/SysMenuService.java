package com.ach.admin.query.service;


import cn.hutool.core.lang.tree.Tree;
import com.ach.admin.dto.menu.MenuDTO;
import com.ach.admin.dto.menu.MenuDetailDTO;
import com.ach.admin.dto.menu.RouterDTO;
import com.ach.admin.entity.SysMenuEntity;
import com.ach.admin.query.MenuQuery;
import com.ach.infrastructure.user.web.SystemLoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysMenuService extends IService<SysMenuEntity> {
    List<MenuDTO> getMenuList(MenuQuery menuQuery);

    MenuDetailDTO getMenuInfo(Long menuId);

    List<Tree<Long>> getDropdownList(SystemLoginUser loginUser);

    /**
     * 根据用户查询系统菜单列表
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenuEntity> getMenuListByUserId(Long userId);

    List<Long> getMenuIdsByRoleId(Long roleId);

    List<RouterDTO> getRouterTree(SystemLoginUser loginUser);
}

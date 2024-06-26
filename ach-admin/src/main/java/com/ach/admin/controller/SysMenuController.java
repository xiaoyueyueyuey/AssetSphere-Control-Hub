package com.ach.admin.controller;


import cn.hutool.core.lang.tree.Tree;
import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.menu.MenuDTO;
import com.ach.admin.dto.menu.MenuDetailDTO;
import com.ach.admin.query.MenuQuery;
import com.ach.admin.service.SysMenuService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.menu.command.AddMenuCommand;
import com.ach.domain.system.menu.command.DeleteMenuCommand;
import com.ach.domain.system.menu.command.UpdateMenuCommand;
import com.ach.domain.system.menu.handler.AddMenuCommandHandler;
import com.ach.domain.system.menu.handler.DeleteMenuCommandHandler;
import com.ach.domain.system.menu.handler.UpdateMenuCommandHandler;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.user.AuthenticationUtils;
import com.ach.infrastructure.user.web.SystemLoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 *
 *
 */
@Tag(name = "菜单API", description = "菜单相关的增删查改")
@RestController
@RequestMapping("/system/menus")
@Validated
@RequiredArgsConstructor
@Slf4j
public class SysMenuController extends BaseController {
    @Resource
    private AddMenuCommandHandler addMenuCommandHandler;
    @Resource
    private UpdateMenuCommandHandler updateMenuCommandHandler;
    @Resource
    private DeleteMenuCommandHandler deleteMenuCommandHandler;
    @Resource
    private CommandInvoker commandInvoker;
    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取菜单列表
     */
    @Operation(summary = "菜单列表")
    @PreAuthorize("@permission.has('system:menu:list')")
    @GetMapping
    public BaseResponseData<List<MenuDTO>> menuList(MenuQuery menuQuery) {
        List<MenuDTO> menuList = sysMenuService.getMenuList(menuQuery);
        return BaseResponseData.ok(menuList);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @Operation(summary = "菜单详情")
    @PreAuthorize("@permission.has('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public BaseResponseData<MenuDetailDTO> menuInfo(@PathVariable("menuId") @NotNull @PositiveOrZero Long menuId) {
        MenuDetailDTO menu = sysMenuService.getMenuInfo(menuId);
        return BaseResponseData.ok(menu);
    }

    /**
     * 获取菜单下拉树列表
     */
    @Operation(summary = "菜单列表（树级）", description = "菜单树级下拉框")
    @GetMapping("/dropdown")
    public BaseResponseData<List<Tree<Long>>> dropdownList() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        List<Tree<Long>> dropdownList = sysMenuService.getDropdownList(loginUser);
        return BaseResponseData.ok(dropdownList);
    }

    /**
     * 新增菜单
     * 需支持一级菜单以及 多级菜单 子菜单为一个 或者 多个的情况
     * 隐藏菜单不显示  以及rank排序
     * 内链 和 外链
     */
    @Operation(summary = "添加菜单")
    @PreAuthorize("@permission.has('system:menu:add')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public BaseResponseData<Void> add(@RequestBody AddMenuCommand addCommand) {
        log.info("添加菜单，addCommand:{}", addCommand);
        Boolean invoke = commandInvoker.execute(addMenuCommandHandler, addCommand);
        if (!invoke) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }
    /**
     * 修改菜单
     */
    @Operation(summary = "编辑菜单")
    @PreAuthorize("@permission.has('system:menu:edit')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{menuId}")
    public BaseResponseData<Void> edit(@PathVariable("menuId") Long menuId, @RequestBody UpdateMenuCommand updateCommand) {
        log.info("编辑菜单，menuId:{}", menuId);
        updateCommand.setMenuId(menuId);
        Boolean invoke = commandInvoker.execute(updateMenuCommandHandler, updateCommand);

        if (!invoke) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 删除菜单
     */
    @Operation(summary = "删除菜单")
    @PreAuthorize("@permission.has('system:menu:remove')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{menuId}")
    public BaseResponseData<Void> remove(@PathVariable("menuId") Long menuId) {
        log.info("删除菜单，menuId:{}", menuId);
        Boolean execute = commandInvoker.execute(deleteMenuCommandHandler, new DeleteMenuCommand(menuId));
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

}

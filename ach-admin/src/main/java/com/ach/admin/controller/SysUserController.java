package com.ach.admin.controller;


import cn.hutool.core.collection.ListUtil;
import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.dto.user.SearchUserDO;
import com.ach.admin.dto.user.UserDTO;
import com.ach.admin.dto.user.UserDetailDTO;
import com.ach.admin.query.SearchUserQuery;
import com.ach.admin.service.SysUserService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.domain.CommandInvoker;
import com.ach.domain.system.user.command.manager.*;
import com.ach.domain.system.user.handler.manager.*;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.page.PageCustomDTO;
import com.ach.infrastructure.utils.poi.CustomExcelUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息,要使用缓存中心，代办
 *
 * 
 */
@Tag(name = "用户API", description = "用户相关的增删查改")
@RestController
@RequestMapping("/system/users")
@RequiredArgsConstructor
@Validated
public class SysUserController extends BaseController {


    private final SysUserService sysUserService;
    private final CommandInvoker commandInvoker;
    private final DeleteUserCommandHandler deleteUserCommandHandler;
    private final UpdateUserCommandHandler updateUserCommandHandler;
    private final AddUserCommandHandler addUserCommandHandler;
    private final ChangeStatusCommandHandler changeStatusCommandHandler;
    private final ResetPasswordCommandHandler resetPasswordCommandHandler;

    /**
     * 获取用户列表
     */
    @Operation(summary = "用户列表")
//    @PreAuthorize("@permission.has('system:user:list') AND @dataScope.checkDeptId(#query.deptId)")
    //方便测试就不加部门权限了
    @PreAuthorize("@permission.has('system:user:list')")
    @GetMapping
    public BaseResponseData<PageCustomDTO<UserDTO>> userList(SearchUserQuery<SearchUserDO> query) {
        Page<SearchUserDO> userPage = sysUserService.getUserList(query);
        List<UserDTO> userDTOList = userPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        PageCustomDTO<UserDTO> userDTOPageCustomDTO = new PageCustomDTO<>(userDTOList, userPage.getTotal());
        return BaseResponseData.ok(userDTOPageCustomDTO);
    }

    @Operation(summary = "用户列表导出")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:user:export')")
    @GetMapping("/excel")
    public void exportUserByExcel(HttpServletResponse response, SearchUserQuery<SearchUserDO> query) {
        Page<SearchUserDO> userPage = sysUserService.getUserList(query);
        List<UserDTO> userDTOList = userPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        PageCustomDTO<UserDTO> userList = new PageCustomDTO<>(userDTOList, userPage.getTotal());
        CustomExcelUtil.writeToResponse(userList.getRows(), UserDTO.class, response);
    }

    @Operation(summary = "用户列表导入")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("@permission.has('system:user:import')")
    @PostMapping("/excel")
    public BaseResponseData<Void> importUserByExcel(MultipartFile file) {
        List<AddUserCommand> commands = CustomExcelUtil.readFromRequest(AddUserCommand.class, file);
        //TODO 优化，批量导入
        for (AddUserCommand command : commands) {
            commandInvoker.execute(addUserCommandHandler, command);
        }
        return BaseResponseData.ok();
    }

    /**
     * 下载批量导入模板
     */
    @Operation(summary = "用户导入excel下载")
    @GetMapping("/excelTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        CustomExcelUtil.writeToResponse(ListUtil.toList(new AddUserCommand()), AddUserCommand.class, response);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @Operation(summary = "用户详情")
    @PreAuthorize("@permission.has('system:user:query')")
    @GetMapping("/{userId}")
    public BaseResponseData<UserDetailDTO> getUserDetailInfo(@PathVariable(value = "userId", required = false) Long userId) {
        UserDetailDTO userDetailInfo = sysUserService.getUserDetailInfo(userId);
        return BaseResponseData.ok(userDetailInfo);
    }

    /**
     * 新增用户
     */
    @Operation(summary = "新增用户")
//    @PreAuthorize("@permission.has('system:user:add') AND @dataScope.checkDeptId(#command.deptId)")
    @PreAuthorize("@permission.has('system:user:add')")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public BaseResponseData<Void> add(@RequestBody AddUserCommand command) {
        Boolean execute = commandInvoker.execute(addUserCommandHandler, command);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 修改用户
     */
    @Operation(summary = "修改用户")
//    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}")
    public BaseResponseData<Void> edit(@Validated @RequestBody UpdateUserCommand command) {
        Boolean execute = commandInvoker.execute(updateUserCommandHandler, command);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 删除用户,支持批量删除
     */
    @Operation(summary = "删除用户")
    @PreAuthorize("@permission.has('system:user:remove') AND @dataScope.checkUserIds(#userIds)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{userIds}")
    public BaseResponseData<Void> remove(@PathVariable("userIds") List<Long> userIds) {
//        BulkOperationCommand<Long> bulkDeleteCommand = new BulkOperationCommand<>(userIds);
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand();
        for (Long userId : userIds) {
            //TODO待优化，批量删除
            deleteUserCommand.setUserId(userId);
            commandInvoker.execute(deleteUserCommandHandler, deleteUserCommand);
        }
        return BaseResponseData.ok();
    }

    /**
     * TODO重置密码,待区分用户端和管理端
     */
    @Operation(summary = "重置用户密码")
    @PreAuthorize("@permission.has('system:user:resetPwd') AND @dataScope.checkUserId(#userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/password")
    public BaseResponseData<Void> resetPassword(@PathVariable Long userId, @RequestBody ResetPasswordCommand command) {
        ResetPasswordCommand resetPasswordCommand = new ResetPasswordCommand();
        resetPasswordCommand.setUserId(userId);

        Boolean execute = commandInvoker.execute(resetPasswordCommandHandler, resetPasswordCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 状态修改
     */
    @Operation(summary = "修改用户状态")
//    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @PreAuthorize("@permission.has('system:user:edit') ")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/status")
    public BaseResponseData<Void> changeStatus(@PathVariable("userId") Long userId, @RequestBody ChangeStatusCommand command) {
        command.setUserId(userId);
        commandInvoker.execute(changeStatusCommandHandler, command);
        return BaseResponseData.ok();
    }


}

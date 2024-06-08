package com.ach.admin.controller;


import com.ach.admin.common.dto.common.UploadFileDTO;
import com.ach.admin.customize.aop.accessLog.AccessLog;
import com.ach.admin.domain.common.CommandInvoker;
import com.ach.admin.dto.user.UserProfileDTO;
import com.ach.admin.query.service.SysUserService;
import com.ach.common.base.BaseResponseData;
import com.ach.common.constant.Constants;
import com.ach.common.enums.common.BusinessTypeEnum;
import com.ach.common.exception.ApiException;
import com.ach.common.exception.error.ErrorCode;
import com.ach.domain.system.user.command.user.UpdateUserAvatarCommand;
import com.ach.domain.system.user.command.user.UpdateUserPasswordCommand;
import com.ach.domain.system.user.command.user.UpdateUserProfileCommand;
import com.ach.domain.system.user.handler.user.UpdateProfileCommandHandler;
import com.ach.domain.system.user.handler.user.UpdateUserAvatarCommandHandler;
import com.ach.domain.system.user.handler.user.UpdateUserPasswordCommandHandler;
import com.ach.infrastructure.base.BaseController;
import com.ach.infrastructure.user.AuthenticationUtils;
import com.ach.infrastructure.user.web.SystemLoginUser;
import com.ach.infrastructure.utils.file.FileUploadUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@Tag(name = "个人信息API", description = "个人信息相关接口")
@RestController
@RequestMapping("/system/user/profile")
@RequiredArgsConstructor
public class SysUserProfileController extends BaseController {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private UpdateUserAvatarCommandHandler updateUserAvatarCommandHandler;
    @Resource
    private UpdateProfileCommandHandler updateProfileCommandHandler;
    @Resource
    private UpdateUserPasswordCommandHandler updateUserPasswordCommandHandler;
    @Resource
    private CommandInvoker commandInvoker;

    /**
     * 个人信息
     */
    @Operation(summary = "获取个人信息")
    @GetMapping
    public BaseResponseData<UserProfileDTO> profile() {
        SystemLoginUser user = AuthenticationUtils.getSystemLoginUser();
        UserProfileDTO userProfile = sysUserService.getUserProfile(user.getUserId());
        return BaseResponseData.ok(userProfile);
    }

    /**
     * 修改用户
     */
    @Operation(summary = "修改个人信息")
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public BaseResponseData<Void> updateProfile(@RequestBody UpdateUserProfileCommand command) {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        command.setUserId(loginUser.getUserId());

        Boolean execute = commandInvoker.execute(updateProfileCommandHandler, command);
        if (!execute) {
            return BaseResponseData.fail();
        }

        return BaseResponseData.ok();
    }

    /**
     * 重置密码
     */
    @Operation(summary = "重置个人密码")
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/password")
    public BaseResponseData<Void> updatePassword(@RequestBody UpdateUserPasswordCommand command) {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        command.setUserId(loginUser.getUserId());

        Boolean execute = commandInvoker.execute(updateUserPasswordCommandHandler, command);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok();
    }

    /**
     * 头像上传
     */
    @Operation(summary = "修改个人头像")
    @AccessLog(title = "用户头像", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/avatar")
    public BaseResponseData<UploadFileDTO> avatar(@RequestParam("avatarfile") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException(ErrorCode.Business.USER_UPLOAD_FILE_FAILED);
        }
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        String avatarUrl = FileUploadUtils.upload(Constants.UploadSubDir.AVATAR_PATH, file);
        UpdateUserAvatarCommand updateUserAvatarCommand = new UpdateUserAvatarCommand(loginUser.getUserId(), avatarUrl);

        Boolean execute = commandInvoker.execute(updateUserAvatarCommandHandler, updateUserAvatarCommand);
        if (!execute) {
            return BaseResponseData.fail();
        }
        return BaseResponseData.ok(new UploadFileDTO(avatarUrl));
    }
}

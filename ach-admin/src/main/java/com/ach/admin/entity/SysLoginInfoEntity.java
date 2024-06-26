package com.ach.admin.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * 
 * @since 2022-10-02
 */
@Data
@TableName("sys_login_info")
@Schema(description = "SysLoginInfoEntity对象 系统访问记录")
public class SysLoginInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2544725455105907785L;
    @Schema(description = "访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    @Schema(description = "用户账号")
    @TableField("username")
    private String username;

    @Schema(description = "登录IP地址")
    @TableField("ip_address")
    private String ipAddress;

    @Schema(description = "登录地点")
    @TableField("login_location")
    private String loginLocation;

    @Schema(description = "浏览器类型")
    @TableField("browser")
    private String browser;

    @Schema(description = "操作系统")
    @TableField("operation_system")
    private String operationSystem;

    @Schema(description = "登录状态（1成功 0失败）")
    @TableField("`status`")
    private Integer status;

    @Schema(description = "提示消息")
    @TableField("msg")
    private String msg;

    @Schema(description = "访问时间")
    @TableField("login_time")
    private Date loginTime;

    @Schema(description = "逻辑删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

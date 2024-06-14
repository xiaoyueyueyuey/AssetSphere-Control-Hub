package com.ach.audit.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Data
@TableName("asset_lending_audit")
public class AssetLendingAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资产审核id（是申请表的id）
     */
    @TableId("lending_id")
    private Long lendingId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核者id
     */
    private Long auditUserId;

}

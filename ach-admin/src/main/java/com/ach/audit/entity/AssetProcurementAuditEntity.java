package com.ach.audit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("asset_procurement_audit")
public class AssetProcurementAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("procurement_id")
    private Long procurementId;

    /**
     * 审核备注
     */
    private String remark;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    /**
     * 审核人id
     */
    private Long auditUserId;


}

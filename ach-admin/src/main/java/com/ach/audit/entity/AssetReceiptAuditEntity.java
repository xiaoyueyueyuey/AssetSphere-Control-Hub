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
@TableName("asset_receipt_audit")
public class AssetReceiptAuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入库id
     */
    @TableId("asset_receipt_id")
    private Long assetReceiptId;

    /**
     * 备注
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
     * 审核者id
     */
    private Long auditUserId;
}

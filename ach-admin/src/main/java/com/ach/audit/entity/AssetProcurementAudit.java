package com.ach.audit.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("asset_procurement_audit")
public class AssetProcurementAudit implements Serializable {

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

    public Long getProcurementId() {
        return procurementId;
    }

    public void setProcurementId(Long procurementId) {
        this.procurementId = procurementId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDateTime auditTime) {
        this.auditTime = auditTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    @Override
    public String toString() {
        return "AssetProcurementAudit{" +
                "procurementId = " + procurementId +
                ", remark = " + remark +
                ", deleted = " + deleted +
                ", auditTime = " + auditTime +
                ", auditUserId = " + auditUserId +
                "}";
    }
}

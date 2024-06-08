package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@TableName("asset_lending_record")
public class AssetLendingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 借出id
     */
    @TableId(value = "lending_id", type = IdType.AUTO)
    private Long lendingId;

    /**
     * 借用者
     */
    private Long userId;

    /**
     * 借用资产id
     */
    private Long assetId;

    /**
     * 借出时间
     */
    private LocalDate loanTime;

    /**
     * 归还时间
     */
    private LocalDate returnTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 0 待审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未归还,1已归还
     */
    private Byte returnStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    public Long getLendingId() {
        return lendingId;
    }

    public void setLendingId(Long lendingId) {
        this.lendingId = lendingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public LocalDate getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(LocalDate loanTime) {
        this.loanTime = loanTime;
    }

    public LocalDate getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDate returnTime) {
        this.returnTime = returnTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Byte getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(Byte returnStatus) {
        this.returnStatus = returnStatus;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AssetLendingRecord{" +
                "lendingId = " + lendingId +
                ", userId = " + userId +
                ", assetId = " + assetId +
                ", loanTime = " + loanTime +
                ", returnTime = " + returnTime +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", remark = " + remark +
                ", auditStatus = " + auditStatus +
                ", returnStatus = " + returnStatus +
                ", deleted = " + deleted +
                "}";
    }
}

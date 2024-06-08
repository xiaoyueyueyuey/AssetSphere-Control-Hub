package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@TableName("asset_procurement_record")
public class AssetProcurementRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 采购id
     */
    @TableId(value = "procurement_id", type = IdType.AUTO)
    private Long procurementId;

    /**
     * 申请人
     */
    private Long userId;

    /**
     * 采购所需费用
     */
    private BigDecimal money;

    /**
     * 采购内容
     */
    private String remark;

    /**
     * 申请时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未采购 1正在采购 2采购完成
     */
    private Byte procurementStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    public Long getProcurementId() {
        return procurementId;
    }

    public void setProcurementId(Long procurementId) {
        this.procurementId = procurementId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Byte getProcurementStatus() {
        return procurementStatus;
    }

    public void setProcurementStatus(Byte procurementStatus) {
        this.procurementStatus = procurementStatus;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AssetProcurementRecord{" +
                "procurementId = " + procurementId +
                ", userId = " + userId +
                ", money = " + money +
                ", remark = " + remark +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", auditStatus = " + auditStatus +
                ", procurementStatus = " + procurementStatus +
                ", deleted = " + deleted +
                "}";
    }
}

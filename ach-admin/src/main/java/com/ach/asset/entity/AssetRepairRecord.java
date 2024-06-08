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
@TableName("asset_repair_record")
public class AssetRepairRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报修id
     */
    @TableId(value = "repair_id", type = IdType.AUTO)
    private Long repairId;

    /**
     * 申请人id
     */
    private Byte userId;

    /**
     * 资产id
     */
    private Long assetId;

    /**
     * 报修金额
     */
    private BigDecimal money;

    /**
     * 备注
     */
    private String remark;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    /**
     * 0未审核 1成功 2拒绝
     */
    private Byte auditStatus;

    /**
     * 0未维修，1正在维修，2维修成功
     */
    private Byte repairStatus;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Byte getUserId() {
        return userId;
    }

    public void setUserId(Byte userId) {
        this.userId = userId;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
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

    public Byte getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(Byte repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AssetRepairRecord{" +
                "repairId = " + repairId +
                ", userId = " + userId +
                ", assetId = " + assetId +
                ", money = " + money +
                ", remark = " + remark +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", auditStatus = " + auditStatus +
                ", repairStatus = " + repairStatus +
                ", deleted = " + deleted +
                "}";
    }
}

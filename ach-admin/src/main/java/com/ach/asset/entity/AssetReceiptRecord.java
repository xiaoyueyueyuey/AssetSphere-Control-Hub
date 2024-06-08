package com.ach.asset.entity;

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
@TableName("asset_receipt_record")
public class AssetReceiptRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入库id
     */
    @TableId("asset_receipt_id")
    private Long assetReceiptId;

    /**
     * 入库人
     */
    private Long userId;

    /**
     * 资产型号
     */
    private String assetModel;

    /**
     * 入库数量
     */
    private Long count;

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
     * 存放房间id
     */
    private Integer roomId;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    public Long getAssetReceiptId() {
        return assetReceiptId;
    }

    public void setAssetReceiptId(Long assetReceiptId) {
        this.assetReceiptId = assetReceiptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "AssetReceiptRecord{" +
                "assetReceiptId = " + assetReceiptId +
                ", userId = " + userId +
                ", assetModel = " + assetModel +
                ", count = " + count +
                ", remark = " + remark +
                ", createTime = " + createTime +
                ", updateTime = " + updateTime +
                ", auditStatus = " + auditStatus +
                ", roomId = " + roomId +
                ", deleted = " + deleted +
                "}";
    }
}

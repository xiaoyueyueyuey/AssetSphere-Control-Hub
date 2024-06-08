package com.ach.location.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@TableName("asset_storage_room")
public class AssetStorageRoom implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存储室Id
     */
    @TableId("room_id")
    private Integer roomId;

    /**
     * 存储室名
     */
    private String roomName;

    /**
     * 所属区域名
     */
    private String locationName;

    /**
     * 所属区域id
     */
    private Integer locationId;

    /**
     * 0正常1停用
     */
    private Byte status;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    /**
     * 备注
     */
    private String remark;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AssetStorageRoom{" +
                "roomId = " + roomId +
                ", roomName = " + roomName +
                ", locationName = " + locationName +
                ", locationId = " + locationId +
                ", status = " + status +
                ", deleted = " + deleted +
                ", remark = " + remark +
                "}";
    }
}

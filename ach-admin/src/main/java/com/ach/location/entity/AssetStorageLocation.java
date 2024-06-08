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
@TableName("asset_storage_location")
public class AssetStorageLocation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域（哪栋楼）
     */
    @TableId("location_id")
    private Integer locationId;

    /**
     * 区域名
     */
    private String localtionName;

    /**
     * 0正常1停用
     */
    private Byte status;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocaltionName() {
        return localtionName;
    }

    public void setLocaltionName(String localtionName) {
        this.localtionName = localtionName;
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

    @Override
    public String toString() {
        return "AssetStorageLocation{" +
                "locationId = " + locationId +
                ", localtionName = " + localtionName +
                ", status = " + status +
                ", deleted = " + deleted +
                "}";
    }
}

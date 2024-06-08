package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("asset_classification")
public class AssetClassification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资产分类id
     */
    @TableId(value = "ac_id", type = IdType.AUTO)
    private Integer acId;

    /**
     * 资产分类名
     */
    private String acName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    /**
     * 0启用 1停用
     */
    private Byte status;

    public Integer getAcId() {
        return acId;
    }

    public void setAcId(Integer acId) {
        this.acId = acId;
    }

    public String getAcName() {
        return acName;
    }

    public void setAcName(String acName) {
        this.acName = acName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AssetClassification{" +
                "acId = " + acId +
                ", acName = " + acName +
                ", sort = " + sort +
                ", remark = " + remark +
                ", deleted = " + deleted +
                ", status = " + status +
                "}";
    }
}

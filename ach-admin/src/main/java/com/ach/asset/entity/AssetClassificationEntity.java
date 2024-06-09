package com.ach.asset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xy
 * @since 2024-06-08
 */
@Data
@TableName("asset_classification")
public class AssetClassificationEntity implements Serializable {

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

}

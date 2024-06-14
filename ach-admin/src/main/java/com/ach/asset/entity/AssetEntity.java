package com.ach.asset.entity;


import com.baomidou.mybatisplus.annotation.*;
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
@TableName("asset")
@Data
public class AssetEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("asset_id")
    private Long assetId;
    private Integer acId;
    private String acName;

    private String assetName;

    private String assetNumber;

    private String assetModel;

    private String remark;

    private Integer roomId;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Byte status;
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

}

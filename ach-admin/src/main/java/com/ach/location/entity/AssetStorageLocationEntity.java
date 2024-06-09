package com.ach.location.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("asset_storage_location")
@Schema(name = "AssetStorageLocationEntity", title = "教学楼聚合类")
public class AssetStorageLocationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 区域（哪栋楼）
     */
    @Schema(title = "教学楼id")
    @TableId(value = "location_id",type = IdType.AUTO)
    private Integer locationId;

    /**
     * 区域名
     */
    @Schema(title = "教学楼名字")
    private String locationName;

    /**
     * 0正常1停用
     */
    @Schema(title = "状态")
    @TableField(fill = FieldFill.INSERT)
    private Boolean status;

    /**
     * 逻辑删除
     */
    @TableField(fill = FieldFill.INSERT)

    @Schema(title = "逻辑删除")
    private Boolean deleted;

}

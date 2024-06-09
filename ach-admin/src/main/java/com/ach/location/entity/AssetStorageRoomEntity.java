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
@TableName("asset_storage_room")
@Schema(name = "AssetStorageRoomEntity", title = "存储室聚合类")
public class AssetStorageRoomEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存储室Id
     */
    @TableId(value = "room_id",type = IdType.AUTO)
    @Schema(title = "存储室id")
    private Integer roomId;

    /**
     * 存储室名
     */
    @Schema(title = "存储室名字")
    private String roomName;

    /**
     * 所属区域名
     */
    @Schema(title = "所属区域名字")
    private String locationName;

    /**
     * 所属区域id
     */
    @Schema(title = "所属区域id")
    private Integer locationId;

    /**
     * 0正常1停用
     */
    @Schema(title = "状态")
    @TableField(fill = FieldFill.INSERT)
    private Boolean status;

    /**
     * 逻辑删除
     */
    @Schema(title = "逻辑删除")
    @TableLogic()
    @TableField(fill = FieldFill.INSERT)
    private Boolean deleted;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;

    @Schema(title = "排序")
    private Integer roomSort;

}

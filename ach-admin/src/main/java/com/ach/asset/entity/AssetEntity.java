package com.ach.asset.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String assetNumber;

    private String assetModel;

    private Byte status;

    private Byte deleted;

    private String remark;

    private Integer roomId;


}
